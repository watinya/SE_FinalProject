package Member;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;

public class TxtToPdf {
	File f1 = new File( request.getRealPath("") + "/tom.pdf" );

	// �إ� PDF Document ����
	Document document = null;
	int vPageWidth = (int)(Math.round(Double.parseDouble(request.getParameter("pagewidth")) * 72));
	int vPageHeight = (int)(Math.round(Double.parseDouble(request.getParameter("pageheight")) * 72));

	if( request.getParameter("pagewidth") != null && request.getParameter("pageheight") != null )
	{
	   // �]�w Page �d��
	   Rectangle pdfPage = new Rectangle( vPageWidth
	                                    , vPageHeight
	                                    );
	   
	   // �إ� PDF Document ����, �B�ȱi�]�w�H page ���󬰥D
	   document = new Document( pdfPage, 10, 10, 10, 10 );
	}
	else
	   document = new Document();

	try
	{
	    // �N PDF Document �����X���ɮ�
	    PdfWriter.getInstance(document, new FileOutputStream( request.getRealPath("") + "/tom.pdf"));
	    
	    // �}�� PDF �ɮ�
	    document.open();
	    
	    // ��Z�j�p
	    float   vLineSize = 10;
	    
	    // �r��: �ө���
	    BaseFont bf = BaseFont.createFont( request.getRealPath("") + "/mingliu.ttc,0"
	                                     , BaseFont.IDENTITY_H
	                                     , BaseFont.EMBEDDED
	                                     );
	    
	    // �]�w�r���j�p (���Z�p�@�I)
	    Font font = new Font( bf, vLineSize - 1, Font.NORMAL );
	    
	    // Ū�� TEXT �ɮ� (�]�w�s�X�� UTF-8)
	    try
	    {
	      String  str;
	   boolean vPushName = false;
	      
	      // Ū���ӷ��ɮ�
	      BufferedReader br = new BufferedReader( new InputStreamReader( new java.net.URL(vURL).openStream()
	                                                                   , "UTF-8"
	                                                                   )
	                                            );
	      
	      while( (str = br.readLine()) != null )
	      {
	         // Ū�촫���Ÿ�, �۰ʴ���
	         if( str.indexOf("\f") > -1 )
	         {
	            document.newPage();
	            document.add( new Paragraph( vLineSize, str.substring(1), font ) );
	         }
	         else
	         {
	            // ø�s���
	            document.add( new Paragraph( vLineSize, str, font ) );
	            
	            // �ťզC
	            if( str.length() == 0 )
	              document.add( new Paragraph( vLineSize, "\n", font ) );
	         }
	      }
	      
	      // ����귽
	      br.close();
	    }
	    catch( Exception e )
	    {
	      out.println( e.toString() );
	    }
	    
	    // ��� PDF ����
	    response.sendRedirect( "tom.pdf" );
	}
	catch(Exception pdfErr)
	{
	   out.println( "��X PDF ���~ !!" );
	}

	// ���� PDF �ɮ�
	document.close();
}