package com.yks.createpdf;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import net.lemonsoft.lemonbubble.LemonBubble;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Android生成PDF
 * 使用itext生成PDF文件
 */
public class MainActivity extends Activity {

    //危险权限（6.0及以上运行时权限）
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE//用来更新下载时存储APK文件
    };
    private static final int PERMISSION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initView(){
        Button btn_createPDF = (Button) findViewById(R.id.btn_createPDF);
        btn_createPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPDF();
            }
        });
    }
    /**
     * android6.0及以上运行时权限的操作
     * 把应用所需要的运行时权限让用户手动允许
     */
    @Override
    protected void onResume() {
        super.onResume();
        //// TODO: 2016/11/28 权限检测，只有在全部权限都同意的情况下才能进入程序，有一个不同意的话，则继续弹出这个权限的对话框
        PackageManager pkm = this.getPackageManager();//包管理器
        String pkName = this.getPackageName();//应用包名
        int len = PERMISSIONS.length;
        //所有权限是否全部允许
        boolean[] permissions = new boolean[len];
        for (int i = 0; i < len; i++){
            permissions[i] =   (PackageManager.PERMISSION_GRANTED
                    == pkm.checkPermission(PERMISSIONS[i], pkName));
        }
        boolean isAllPermissionAllowed = true;
        int index = 0;
        String[] tempArray = new String[len];
        for (int j = 0 ; j < len ; j++){
            //将不允许的权限放入这个临时的数组里面
            if (!permissions[j]){
                tempArray[index] = PERMISSIONS[j];
                index ++;
                isAllPermissionAllowed = false;
            }
        }
        //得到所有未允许的权限，再次请求
        String[] array = new String[index];
        for (int k = 0 ; k < index ; k++){
            array[k] = tempArray[k];
        }
        if (isAllPermissionAllowed) {// 这里才开始真的干活的
            initView();
        } else {
            ActivityCompat.requestPermissions(this, array, PERMISSION_REQUEST_CODE);
        }
    }

    private void createPDF(){
        BaseFont baseFont;
        try {
            Document document = new Document(PageSize.A4);
            File file = new File("/sdcard/zzh/","test.pdf");//设置文件存放路径和文件名称
            FileOutputStream fos = new FileOutputStream(file);//写入
            PdfWriter.getInstance(document,fos);//pdf初始化

            baseFont = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);//设置语言

            Font font = new Font(baseFont,20,Font.BOLD);//三个参数依次是：字体类型，字体大小，字体样式
            //开始
            document.open();//打开文档页面
            document.setPageCount(TRIM_MEMORY_MODERATE);//设置页数，默认60页
            document.addCreationDate();//加入创建的时间
            document.addCreator("牛叉叉的华爷");//添加创建人姓名

            Paragraph paragraph = new Paragraph("为什么会有热修复这个东西呢？大家都知道如果我们的线上的app 由于某种原因crash？"+"\n"+
                    "我们这时候不能怨测试没测好，后台接口有变化什么的，这不是解决问题的最终方式！"+"\n"+
                    "要是以前我们肯定就是把重新上传app到各大渠道，从新上线，这个过程严重的影响到我们的用户体验非常不好，"+"\n"+
                    "而且很耗时！作为程序员如何通过代码进行线上修复crash bug。。。呢？"+"\n"+
                    "所以有了热修复这个功能 bat 每家都有自己的开源热修复库？我这里就讲一下如何通过反射的方式来实现修复功能吧！"+"\n"+
                    "也就是通过DexClassLoader。如果大家对其他的开源库想要了解的话可以通过一下传送门",font);
            document.add(paragraph);//添加内容
            document.close();//关闭写入
            Toast.makeText(MainActivity.this,"PDF创建成功",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LemonBubble.showError(MainActivity.this,"错误1",2000);
        } catch (DocumentException e) {
            e.printStackTrace();
            LemonBubble.showError(MainActivity.this,"错误2",2000);
        } catch (IOException e) {
            e.printStackTrace();
            LemonBubble.showError(MainActivity.this,"错误3",2000);
        }
    }
}
