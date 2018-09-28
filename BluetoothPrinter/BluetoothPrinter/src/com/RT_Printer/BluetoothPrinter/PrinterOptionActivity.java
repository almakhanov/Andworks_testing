package com.RT_Printer.BluetoothPrinter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;
import com.RT_Printer.BluetoothPrinter.util.Utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PrinterOptionActivity extends Activity{
	private Button mBtnBack = null;
	private Button mBtnPrintText = null;
	private Button mBtnPrintImage = null;
	private Button mBtnPrint1DBarcode = null;
	private Button mBtnPrintTicket = null;
	private Button mBtnPrintTable = null;
	private EditText m1DBarcodeContent = null;
//	private BloothPrinter mBloothPrinter = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.printer_option);
        InitUIControl();
    }



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
//		this.mBloothPrinter = BloothPrinterActivity.mBloothPrinter;
		super.onResume();
	}



	private void InitUIControl(){
    	mBtnBack = (Button)findViewById(R.id.btn_back);
    	mBtnBack.setOnClickListener(mBtnBackOnClickListener);
    	mBtnPrintText = (Button)findViewById(R.id.btn_print_text);
    	mBtnPrintText.setOnClickListener(mBtnPrintTextOnClickListener);
    	mBtnPrintImage = (Button)findViewById(R.id.btn_print_image);
    	mBtnPrintImage.setOnClickListener(mBtnPrintImageOnClickListener);
    	mBtnPrint1DBarcode = (Button)findViewById(R.id.btn_print_barcode);
    	mBtnPrint1DBarcode.setOnClickListener(mBtnPrint1DBarcodeOnClickListener);
    	mBtnPrintTicket = (Button)findViewById(R.id.btn_print_smallticket);
    	mBtnPrintTicket.setOnClickListener(mBtnPrintTicketOnClickListener);
    	mBtnPrintTable = (Button)findViewById(R.id.btn_print_table);
    	mBtnPrintTable.setOnClickListener(mBtnPrintTableOnClickListener);
    	m1DBarcodeContent = (EditText)findViewById(R.id.edt_barcode_content);
    }

	OnClickListener mBtnBackOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};

	OnClickListener mBtnPrintTextOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(BluetoothPrintDriver.IsNoConnection()){
				return;
			}
			BluetoothPrintDriver.Begin();
			String tmpString = PrinterOptionActivity.this.getResources().getString(R.string.print_text_content);
			BluetoothPrintDriver.ImportData(tmpString);
			BluetoothPrintDriver.ImportData("\r");
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();

		}
	};

	OnClickListener mBtnPrintImageOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(BluetoothPrintDriver.IsNoConnection()){
				return;
			}
			InputStream in = null;
			try {
				in = getResources().getAssets().open("Rongta.jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
			/**
			 * ����˵�£�ͬ����ͼƬ���ʹ��BitmapFactory.decodeResource(res, id);
			 * ������Bitmap λͼ��߶�����ԭͼ��2��������ʹ������ȡ�ķ�ʽ����Դ�ļ��ж�ȡ��
			 *	�������Ұ�ͼƬ������Ϊ ��߶�Ϊ 322��С�����ء�����Ҫע���һ��
			 *
			 */
/*			BufferedInputStream bis = new BufferedInputStream(in);
			Bitmap bitmap = BitmapFactory.decodeStream(bis);
			//���ʹ�ӡͼƬǰ��ָ��
			byte[] start = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x1B, 0x40, 0x1B, 0x33, 0x00 };
			mBloothPrinter.Begin();
			mBloothPrinter.ImportData(start, start.length);
			mBloothPrinter.excute();
			mBloothPrinter.ClearData();
			//����ͼƬ����
			byte[] byteImage = Utils.getReadBitMapBytes(bitmap);		
			mBloothPrinter.ImportData(byteImage, byteImage.length);
			mBloothPrinter.excute();
			mBloothPrinter.ClearData();
			//���ͽ���ָ�� Ҫ��������ͼƬ�ڴ�ӡ�ַ���ʱ�������
			byte[] end = { 0x1d, 0x4c, 0x1f, 0x00 };
			mBloothPrinter.ImportData(end, end.length);
			mBloothPrinter.excute();
			mBloothPrinter.ClearData();
*/			
			BluetoothPrintDriver.printImage();
		}
	};

	OnClickListener mBtnPrint1DBarcodeOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(BluetoothPrintDriver.IsNoConnection()){
				return;
			}
			BluetoothPrintDriver.Begin();
			String print1DBarcodeStr = m1DBarcodeContent.getText().toString();
        	int len = print1DBarcodeStr.length();
        	if(len > 16){
        		String tmpString = PrinterOptionActivity.this.getResources().getString(R.string.barcode_input_hint);
    			Utils.ShowMessage(PrinterOptionActivity.this, tmpString);
        		return;
        	}
//        	for(int i=0; i<len; i++){
//        		if(print1DBarcodeStr.charAt(i)<'0' || print1DBarcodeStr.charAt(i)>'9'){
//        			//Utils.ShowMessage(PrinterOptionActivity.this, "�����ַ�ֻ����0��9λ֮�������!");
//        			String tmpString = PrinterOptionActivity.this.getResources().getString(R.string.barcode_input_hint);
//        			Utils.ShowMessage(PrinterOptionActivity.this, tmpString);
//            		return;
//            	}
//        	}
        	BluetoothPrintDriver.AddCodePrint(BluetoothPrintDriver.Code128_B, print1DBarcodeStr);
        	BluetoothPrintDriver.excute();
        	BluetoothPrintDriver.ClearData();
			}
	};

	OnClickListener mBtnPrintTicketOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(BluetoothPrintDriver.IsNoConnection()){
				return;
			}
			String tmpString1 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content1);
			String tmpString2 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content2);
			String tmpString3 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content3);
			String tmpString4 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content4);
			String tmpString5 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content5);
			String tmpString6 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content6);
			String tmpString7 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content7);
			String tmpString8 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content8);
			String tmpString9 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content9);
			String tmpString10 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content10);
			String tmpString11 = PrinterOptionActivity.this.getResources().getString(R.string.print_smallticket_content11);
			
			
			BluetoothPrintDriver.Begin();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.AddAlignMode((byte)1);//����
			BluetoothPrintDriver.SetLineSpace((byte)50);	
			BluetoothPrintDriver.SetZoom((byte)0x11);//���ߣ�����		
			BluetoothPrintDriver.ImportData(tmpString1);
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.AddAlignMode((byte)0);//�����		
			BluetoothPrintDriver.SetZoom((byte)0x00);//Ĭ�Ͽ�ȡ�Ĭ�ϸ߶�
			BluetoothPrintDriver.ImportData(tmpString2);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData(tmpString3);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData(tmpString4);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData("��������������������������������");
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData(tmpString5);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData("��������������������������������");
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData(tmpString6);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData(tmpString7);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData("��������������������������������");
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData(tmpString8);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.SetZoom((byte)0x11);//���ߣ�����	
			BluetoothPrintDriver.ImportData(tmpString9);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData("����������");
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.ImportData(tmpString10);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.SetZoom((byte)0x00);//Ĭ�Ͽ�ȡ�Ĭ�ϸ߶�	
			BluetoothPrintDriver.ImportData(tmpString11);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();

		}
	};

	OnClickListener mBtnPrintTableOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(BluetoothPrintDriver.IsNoConnection()){
				return;
			}
			String tmpString1 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content1);
			String tmpString2 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content2);
			String tmpString3 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content3);
			String tmpString4 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content4);
			String tmpString5 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content5);
			String tmpString6 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content6);
			String tmpString7 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content7);
			String tmpString8 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content8);
			String tmpString9 = PrinterOptionActivity.this.getResources().getString(R.string.print_table_content9);
			
			
			BluetoothPrintDriver.Begin();
			BluetoothPrintDriver.ImportData(new byte[]{0x1d,0x21,0x01}, 3);	//���ñ���
			BluetoothPrintDriver.ImportData(String.format(tmpString1),true);
			BluetoothPrintDriver.ImportData(String.format(tmpString2),true);
			BluetoothPrintDriver.ImportData(new byte[]{0x1d,0x21,0x01},3);	//���ò�����
			BluetoothPrintDriver.ImportData(String.format(tmpString3),true);
			BluetoothPrintDriver.ImportData(new byte[]{0x1d,0x21,0x01}, 3);	//���ñ���
			BluetoothPrintDriver.ImportData(String.format(tmpString4),true);
			BluetoothPrintDriver.ImportData(new byte[]{0x1d,0x21,0x01},3);	//���ò�����
			BluetoothPrintDriver.ImportData(String.format(tmpString5), true);
			BluetoothPrintDriver.ImportData(new byte[]{0x1d,0x21,0x01},3);	//���ñ���
			BluetoothPrintDriver.ImportData(String.format(tmpString6),true);
			BluetoothPrintDriver.ImportData(new byte[]{0x1d,0x21,0x01},3);	//���ò�����
			BluetoothPrintDriver.ImportData(String.format(tmpString7),true);
			BluetoothPrintDriver.ImportData(new byte[]{0x1d,0x21,0x01},3);	//���ñ���
			BluetoothPrintDriver.ImportData(String.format(tmpString8),true);
			BluetoothPrintDriver.ImportData(new byte[]{0x1d,0x21,0x00},3);	//���ò�����
			BluetoothPrintDriver.ImportData(String.format(tmpString9),true);
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.LF();
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
		}
	};
}
