package com.shen.ssmschoolshop.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.shen.ssmschoolshop.entity.*;
import com.shen.ssmschoolshop.service.CateService;
import com.shen.ssmschoolshop.service.GoodsService;
import com.shen.ssmschoolshop.util.ImageUtil;
import com.shen.ssmschoolshop.util.Msg;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 商家后台管理员编辑商品的信息后点击提交
     * @param pn
     * @param response
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/showjson")
    @ResponseBody
    public Msg getAllGoods(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) { ;
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
        //一页显示几个数据
        PageHelper.startPage(pn, 10);

        List<Goods> employees = goodsService.selectByExample(new GoodsExample());
        for(Goods good:employees){
            System.out.println(good);
        }
        //显示几个页号
        PageInfo page = new PageInfo(employees, 5);

        model.addAttribute("pageInfo", page);

        return Msg.success("查询成功!").add("pageInfo", page);
    }

    /**
     * 商家后台管理员商品管理查询
     * @param pn
     * @param response
     * @param model
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/show")
    public String goodsManage(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        List<Category> categoryList = cateService.selectByExample(new CategoryExample());
        model.addAttribute("categoryList", categoryList);

        return "adminAllGoods";
    }

    @ResponseBody
    @RequestMapping("/exportExcel.do")
    public ResultVO exportExcel(HttpServletRequest request,exportGoodsVO goods){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }
        String[] goodsid = goods.getGoodsid().split("\\|");
        String[] goodsname = goods.getGoodsname().split("\\|");
        String[] price = goods.getPrice().split("\\|");
        String[] num = goods.getNum().split("\\|");
        String[] detailcate = goods.getDetailcate().split("\\|");
        String[] activityid = goods.getActivityid().split("\\|");
        long l = System.currentTimeMillis();
        System.out.println("准备输出："+ l);
        poiUtil(goodsid,goodsname,price,num,detailcate,activityid);
        long l1 = new Date().getTime() - l;
        System.out.println("输出完毕，用时(s)：" + l1/1000);
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setDesc("输出excel完毕");
        return resultVO;
    }

    //POI输出工具类
    public void poiUtil(String[] goodsid,String[] goodsname,String[] price,String[] num,String[] detailcate,String[] activityid){
        Workbook workbook = new SXSSFWorkbook(-1);//新建Java工作簿
        Sheet sheet1 = workbook.createSheet("sheet1");//给这张工作簿创建一张sheet并且取名字
        for (int a=0;a<goodsid.length+1;a++){
            sheet1.createRow(a);
        }
        head(sheet1);//填充头部
        //填充各个字段
        fill(goodsid,sheet1,0);
        fill(goodsname,sheet1,1);
        fill(price,sheet1,2);
        fill(num,sheet1,3);
        fill(detailcate,sheet1,4);
        fill(activityid,sheet1,5);

        /*
        CellStyle cellStyle = workbook.createCellStyle();//通过工作簿封装的方法 新建单元格样式对象
//下面开始封装单元格样式对象 到时候哪个需要此样式的单元格直接setCellStyle(cellStyle)即可
        cellStyle.setBorderBottom(HSSFCellStyle.ALIGN_CENTER);//HSSFCellStyle里面定义了许多静态变量 设置下边框样式
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置样式 水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//设置样式 垂直居中
        DataFormat dataFormat = workbook.createDataFormat();//由于HSSFDataFormar构造方法不是public 只能用工作簿去获取格式化构造方法
        cellStyle.setDataFormat(dataFormat.getFormat("0.0")); //由于setDataFormat()是short类型，所以我们使用getFormat()也要选short类型的
        cell13.setCellStyle(cellStyle);//给这个单元格设置上面封装的单元格样式
        cell01.setCellStyle(cellStyle);//给这个单元格设置上面封装的单元格样式

//设置表的行高列宽
        System.out.println(workbook.getNumberOfSheets());//获取该工作簿的sheet表数量
        Sheet sheet = workbook.getSheetAt(1); //获得该工作簿指定的第几张表
        sheet.setDefaultColumnWidth(2000);//设置指定sheet的默认列宽
        sheet.setDefaultRowHeight((short)2000);//设置指定sheet的默认行高
        */

        OutputStream output = null;//定义文件输出流
        try {
            Random random = new Random(100);
            int k = random.nextInt();
            output = new FileOutputStream("d:\\毕设excel\\商品"+ System.currentTimeMillis() +"excel.xls");
            workbook.write(output);//把工作簿输出到文件输出流指定的位置
        } catch (Exception e) {
            System.out.println("异常抛出");
            e.printStackTrace();
        }

    }

    public static void head(Sheet sheet1){
        Row row0 = sheet1.getRow(0);
        Cell cell0 = row0.createCell(0);
        Cell cell1 = row0.createCell(1);
        Cell cell2 = row0.createCell(2);
        Cell cell3 = row0.createCell(3);
        Cell cell4 = row0.createCell(4);
        Cell cell5 = row0.createCell(5);
        //设置第一行头部
        cell0.setCellValue("商品id");
        cell1.setCellValue("商品名称");
        cell2.setCellValue("商品价格");
        cell3.setCellValue("商品数量");
        cell4.setCellValue("商品类别");
        cell5.setCellValue("活动id");
    }

    //传入数组与指定sheet表，将数组填充到指定列 行数记得递增
    public static void fill(String[] temp,Sheet sheet,int j){
        int i = 1;
        /*for (String s : temp) {
            Row row = sheet.createRow(i++);
            Cell cell = row.createCell(j);
            cell.setCellValue(s);
        }*/
        for(int z=0;z<temp.length;z++){
            Row row = sheet.getRow(i++);
            Cell cell = row.createCell(j);
            cell.setCellValue(temp[z]);
        }
        System.out.println(sheet.getRow(1).getCell(0));
        System.out.println(sheet.getRow(2).getCell(0));
        System.out.println(sheet.getRow(3).getCell(0));
    }

    @RequestMapping("/add")
    public String showAdd(@ModelAttribute("succeseMsg") String msg, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        if (!msg.equals("")) {
            model.addAttribute("msg", msg);
        }

        List<Category> categoryList = cateService.selectByExample(new CategoryExample());
        model.addAttribute("categoryList", categoryList);


        //还需要查询分类传给addGoods页面
        return "addGoods";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Msg updateGoods(Goods goods, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
        /* goods.setGoodsid(goodsid);*/
        goodsService.updateGoodsById(goods);
        return Msg.success("更新成功!");
    }

    @RequestMapping(value = "/delete/{goodsid}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteGoods(@PathVariable("goodsid") Integer goodsid) {
        goodsService.deleteGoodsById(goodsid);
        return Msg.success("删除成功!");
    }

    /**
     * 后台管理 -> 商品管理 -> 添加商品
     * @param goods
     * @param fileToUpload
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     * @throws IOException
     */
    @RequestMapping("/addGoodsSuccess")
    public String addGoods(Goods goods,//直接用GoodsPO类去接收，直接子属性对接映射
                           @RequestParam MultipartFile[] fileToUpload,//上传图片可能不止一张，所以用数组接收
                           HttpServletRequest request,
                           HttpServletResponse response,
                           RedirectAttributes redirectAttributes) throws IOException {
        /*goods.setCategory(1);*/
        goods.setUptime(new Date());
        //添加商品默认情况下,设置成活动ID为1的活动
        goods.setActivityid(1);
        goodsService.addGoods(goods);
        for (MultipartFile multipartFile : fileToUpload) {
            String fileName = goods.getGoodsname()+ multipartFile.getOriginalFilename();
            if (multipartFile != null) {
                //文件名加UUID处理 以及 将图片存放到指定的硬盘当中
               String ImagePath= ImageUtil.imagePath(multipartFile,fileName);
               System.out.println("最后存入数据的图片名字为:"+ImagePath);
                //把图片路径存入数据库中  不但是要将图片存到指定硬盘，而且路径也要保存到库中(全路径还是相对路径自己考虑)
              goodsService.addImagePath(new ImagePath(null, goods.getGoodsid(), ImagePath));

            }
        }
        //由于跳转方式是重定向，不共享request域，但是想传参，所以需要在这里添加key/value
        redirectAttributes.addFlashAttribute("succeseMsg", "商品添加成功!");

        return "redirect:/admin/goods/add";
    }

    @RequestMapping("/addCategory")
    public String addcategory(@ModelAttribute("succeseMsg") String msg, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or();
        List<Category> categoryList;
        categoryList = cateService.selectByExample(categoryExample);
        model.addAttribute("categoryList", categoryList);
        if (!msg.equals("")) {
            model.addAttribute("msg", msg);
        }
        return "addCategory";
    }

    @Autowired
    private CateService cateService;

    @RequestMapping("/addCategoryResult")
    public String addCategoryResult(Category category, Model addCategoryResult, RedirectAttributes redirectAttributes) {
        List<Category> categoryList = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCatenameEqualTo(category.getCatename());
        categoryList = cateService.selectByExample(categoryExample);
        if (!categoryList.isEmpty()) {
            redirectAttributes.addAttribute("succeseMsg", "分类已存在");
            return "redirect:/admin/goods/addCategory";
        } else {
            cateService.insertSelective(category);
            redirectAttributes.addFlashAttribute("succeseMsg", "分类添加成功!");
            return "redirect:/admin/goods/addCategory";
        }
    }

    @RequestMapping("/saveCate")
    @ResponseBody
    public Msg saveCate(Category category) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCatenameEqualTo(category.getCatename());
        List<Category> categoryList = cateService.selectByExample(categoryExample);
        if (categoryList.isEmpty()) {
            cateService.updateByPrimaryKeySelective(category);
            return Msg.success("更新成功");
        } else return Msg.success("名字已经存在");
    }

    @RequestMapping("/deleteCate")
    @ResponseBody
    public Msg deleteCate(Category category) {
        cateService.deleteByPrimaryKey(category.getCateid());
        return Msg.success("删除成功");
    }
}
