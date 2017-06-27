# AndroidCreatePDF
利用iText生成PDF文件

需求：在开发过程中，我们可能会需要将一些数据或者文字内容之类的生成一个PDF文件，PDF文件的好处是内容不可修改，可以适应各种手机尺寸。
但是一直以来都只是用框架打开PDF，很少有在Android上生成PDF的工具，今天特意去找了一下，发现iText这个工具就可以这样实现，具体的原理不是很懂。哈哈
但自己在实现的这个过程中还是遇到了几个坑，故将自己写的示例代码放上来，让有需要的人可以参考下。


--->参考文章：

http://blog.csdn.net/u010276653/article/details/39693619

http://blog.csdn.net/id19870510/article/details/6105574

来看下效果图吧先。
![](https://github.com/zhongzhihuashanghai/AndroidCreatePDF/blob/master/CreatePDF/app/src/main/res/drawable/a1.jpg)
![](https://github.com/zhongzhihuashanghai/AndroidCreatePDF/blob/master/CreatePDF/app/src/main/res/drawable/a2.jpg)
![](https://github.com/zhongzhihuashanghai/AndroidCreatePDF/blob/master/CreatePDF/app/src/main/res/drawable/a3.jpg)

注意事项：1，将两个jar包放入libs目录下面；2，生成PDF文件。

bug：因为PDF生成是不支持中文等亚洲语言的，所以要引入iTextAsian.jar。原先在网上找了一个这样的jar包放进去，运行的时候死活不能成功，说是语言包错误

com.itextpdf.text.DocumentException: Font 'STSong-Light' with 'UniGB-UCS2-H' is not recognized.

在网上百度了一下，还是有大神很厉害呀，说的是这个包其实是旧的，里面的文件名字与现在的不符，所以需要将jar包先解压，把文件名lowagie改成itextpdf，然后再把文件夹压缩成jar包，这样放进去一运行，嘿，发现一下子就成功了，这很神奇吧，哈哈。
参考文章：http://blog.csdn.net/id19870510/article/details/6105574

希望大家多多交流。QQ:903111844

