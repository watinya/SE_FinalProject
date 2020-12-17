package Member;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;

public class TxtToPdf {
	File f1 = new File( request.getRealPath("") + "/tom.pdf" );

	// 建立 PDF Document 物件
	Document document = null;
	int vPageWidth = (int)(Math.round(Double.parseDouble(request.getParameter("pagewidth")) * 72));
	int vPageHeight = (int)(Math.round(Double.parseDouble(request.getParameter("pageheight")) * 72));

	if( request.getParameter("pagewidth") != null && request.getParameter("pageheight") != null )
	{
	   // 設定 Page 範圍
	   Rectangle pdfPage = new Rectangle( vPageWidth
	                                    , vPageHeight
	                                    );
	   
	   // 建立 PDF Document 物件, 且紙張設定以 page 物件為主
	   document = new Document( pdfPage, 10, 10, 10, 10 );
	}
	else
	   document = new Document();

	try
	{
	    // 將 PDF Document 物件輸出到檔案
	    PdfWriter.getInstance(document, new FileOutputStream( request.getRealPath("") + "/tom.pdf"));
	    
	    // 開啟 PDF 檔案
	    document.open();
	    
	    // 行距大小
	    float   vLineSize = 10;
	    
	    // 字型: 細明體
	    BaseFont bf = BaseFont.createFont( request.getRealPath("") + "/mingliu.ttc,0"
	                                     , BaseFont.IDENTITY_H
	                                     , BaseFont.EMBEDDED
	                                     );
	    
	    // 設定字型大小 (比行距小一點)
	    Font font = new Font( bf, vLineSize - 1, Font.NORMAL );
	    
	    // 讀取 TEXT 檔案 (設定編碼為 UTF-8)
	    try
	    {
	      String  str;
	   boolean vPushName = false;
	      
	      // 讀取來源檔案
	      BufferedReader br = new BufferedReader( new InputStreamReader( new java.net.URL(vURL).openStream()
	                                                                   , "UTF-8"
	                                                                   )
	                                            );
	      
	      while( (str = br.readLine()) != null )
	      {
	         // 讀到換頁符號, 自動換頁
	         if( str.indexOf("\f") > -1 )
	         {
	            document.newPage();
	            document.add( new Paragraph( vLineSize, str.substring(1), font ) );
	         }
	         else
	         {
	            // 繪製資料
	            document.add( new Paragraph( vLineSize, str, font ) );
	            
	            // 空白列
	            if( str.length() == 0 )
	              document.add( new Paragraph( vLineSize, "\n", font ) );
	         }
	      }
	      
	      // 釋放資源
	      br.close();
	    }
	    catch( Exception e )
	    {
	      out.println( e.toString() );
	    }
	    
	    // 顯示 PDF 網頁
	    response.sendRedirect( "tom.pdf" );
	}
	catch(Exception pdfErr)
	{
	   out.println( "輸出 PDF 錯誤 !!" );
	}

	// 關閉 PDF 檔案
	document.close();
}