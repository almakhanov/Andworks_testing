
package com.RT_Printer.BluetoothPrinter;



import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;
import com.RT_Printer.BluetoothPrinter.util.Utils;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class BloothPrinterActivity extends Activity {
    /** Called when the activity is first created. */
	public static BluetoothAdapter myBluetoothAdapter;
	public String SelectedBDAddress;
	private Button mBtnConnetBluetoothDevice = null;
	private Button mBtnQuit = null;
	private Button mBtnPrint = null;
	private Button mBtnPrintOption = null;
	private Button mBtnTest = null;
	private EditText mPrintContent = null;
	private CheckBox mBeiKuan = null;
	private CheckBox mUnderline = null;
	private CheckBox mBold = null;
	private CheckBox mBeiGao = null;
	private CheckBox mMinifont = null;
	private CheckBox mHightlight = null;
//	public static BloothPrinter mBloothPrinter = null;
	
	// Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

	// Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.bluetooth_unconnected);
        
        SelectedBDAddress = "";
        if((myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter())==null)
        {
     		Toast.makeText(this,"Did not find the Bluetooth adapter", Toast.LENGTH_LONG).show();//û���ҵ�����������
        }
        if(!myBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);    
            startActivityForResult(enableBtIntent, 2);
        }
        
        
        InitUIControl();
//        mBloothPrinter = BloothPrinter.getInstance();
    }


	private void InitUIControl(){
    	mBtnQuit = (Button)findViewById(R.id.btn_quit);
    	mBtnQuit.setOnClickListener(mBtnQuitOnClickListener);
    	mBtnConnetBluetoothDevice = (Button)findViewById(R.id.btn_connect_bluetooth_device);
    	mBtnConnetBluetoothDevice.setOnClickListener(mBtnConnetBluetoothDeviceOnClickListener);
    	mBtnPrint = (Button)findViewById(R.id.btn_print);
    	mBtnPrint.setOnClickListener(mBtnPrintOnClickListener);
    	mBtnPrintOption = (Button)findViewById(R.id.btn_option);
    	mBtnPrintOption.setOnClickListener(mBtnPrintOptionOnClickListener);
    	mBtnTest = (Button)findViewById(R.id.btn_test);
    	mBtnTest.setOnClickListener(mBtnTestOnClickListener);
    	mPrintContent = (EditText)findViewById(R.id.edt_print_content);
    	mBeiKuan = (CheckBox)findViewById(R.id.checkbox_beikuan);
    	mUnderline = (CheckBox)findViewById(R.id.checkbox_underline);
    	mBold = (CheckBox)findViewById(R.id.checkbox_bold);
    	mBeiGao = (CheckBox)findViewById(R.id.checkbox_beigao);
    	mMinifont = (CheckBox)findViewById(R.id.checkbox_minifont);
    	mHightlight = (CheckBox)findViewById(R.id.checkbox_hightlight);
    }



    @Override
	protected void onResume() {
		super.onResume();
	}



	@Override
	protected void onDestroy() {
		//�˳�ǰ�ر�����
		BluetoothPrintDriver.close();
		super.onDestroy();
	}


	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
	        switch (requestCode) {
	        case REQUEST_CONNECT_DEVICE_SECURE:
	            // When DeviceListActivity returns with a device to connect
	            if (resultCode == Activity.RESULT_OK) 
	            {
	            	// ������һ���豸֮ǰ�ȹر��������������豸֮���л�ʱ�����
	            	BluetoothPrintDriver.close();  
	            	// ��ȡ�豸 MAC address
	            	SelectedBDAddress = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
	            	// ��������
	            	if(!BluetoothPrintDriver.OpenPrinter(SelectedBDAddress)) 	
	            	{
	            		BluetoothPrintDriver.close();
	            		BloothPrinterActivity.this.setTitle(R.string.bluetooth_connect_fail);
//	            		mTitle.setText("����ʧ��");
	                	return;
	            	}
	            	else
	            	{
	            		// ���ӳɹ�����ʾ�豸��MAC��ַ
//	            		mTitle.setText(SelectedBDAddress);
	   				 	String bluetoothConnectSucess = BloothPrinterActivity.this.getResources().getString(R.string.bluetooth_connect_sucess);
	   				 	BloothPrinterActivity.this.setTitle(bluetoothConnectSucess+SelectedBDAddress);
	            	}
	            }
	            break;
	        case REQUEST_CONNECT_DEVICE_INSECURE:
	            // When DeviceListActivity returns with a device to connect
	            if (resultCode == Activity.RESULT_OK) {
	;//                connectDevice(data, false);
	            }
	            break;
	        }
		 
//		 //���Ը��ݶ���������������Ӧ�Ĳ���
//		 if(10 == resultCode)  {
//			 if(GlobalVariable.gOutputStream != null){
//				 String bluetoothDeviceName = data.getExtras().getString("bluetooth_devicename");
//				 String bluetoothConnectSucess = BloothPrinterActivity.this.getResources().getString(R.string.bluetooth_connect_sucess);
//				 BloothPrinterActivity.this.setTitle(bluetoothConnectSucess+bluetoothDeviceName);
//				 mBloothPrinter.setOutputStream(GlobalVariable.gOutputStream);
//				 //mBloothPrinter.Begin();
//			 }
//		 }
//		 if(20 == resultCode)  {
//			 BloothPrinterActivity.this.setTitle(R.string.bluetooth_connect_fail);
//			 mBloothPrinter.setOutputStream(null);
//		 }
//		 if(30 == resultCode)  {
//			 BloothPrinterActivity.this.setTitle(R.string.bluetooth_connected_cannot_connect);
//		 }
//		 if(GlobalVariable.gOutputStream == null){
//			 BloothPrinterActivity.this.setTitle(R.string.bluetooth_connect_fail);
//		 }
//		 super.onActivityResult(requestCode, resultCode, data);
	 }


	OnClickListener mBtnQuitOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//�˳�ǰ�ر�����
			BluetoothPrintDriver.close();
			finish();
		}
	};

	OnClickListener mBtnConnetBluetoothDeviceOnClickListener = new OnClickListener() {
		Intent serverIntent = null;
		public void onClick(View arg0)
		{
			// Launch the DeviceListActivity to see devices and do scan
            serverIntent = new Intent(BloothPrinterActivity.this, DeviceListActivity.class);
            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
		}
	};

	OnClickListener mBtnPrintOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(BluetoothPrintDriver.IsNoConnection()){
				return;
			}
			BluetoothPrintDriver.Begin();
			if(mBeiKuan.isChecked()){
				BluetoothPrintDriver.SetZoom((byte)0x10);
			}
			if(mBeiGao.isChecked()){
				BluetoothPrintDriver.SetZoom((byte)0x01);
			}
			if(mUnderline.isChecked()){
				BluetoothPrintDriver.SetUnderline((byte)0x02);//�»���
			}
			if(mBold.isChecked()){
				BluetoothPrintDriver.AddBold((byte)0x01);//����
			}
			if(mMinifont.isChecked()){
				BluetoothPrintDriver.SetCharacterFont((byte)0x01);
			}
			if(mHightlight.isChecked()){
				BluetoothPrintDriver.AddInverse((byte)0x01);
			}
			String tmpContent = mPrintContent.getText().toString();
			BluetoothPrintDriver.ImportData(tmpContent);
			BluetoothPrintDriver.ImportData("\r");
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
		}
	};

	OnClickListener mBtnPrintOptionOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(BloothPrinterActivity.this, PrinterOptionActivity.class);
			//intent.putExtra("mBloothPrinter", mBloothPrinter);
			startActivity(intent);
		}
	};
	OnClickListener mBtnTestOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(BluetoothPrintDriver.IsNoConnection()){
				return;
			}
			BluetoothPrintDriver.Begin();
			BluetoothPrintDriver.ImportData(new byte[]{0x12,0x54},2);	//��ӡ�Բ�ҳ
			BluetoothPrintDriver.excute();
			BluetoothPrintDriver.ClearData();
		}
	};
}