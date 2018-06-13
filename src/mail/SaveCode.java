package mail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class SaveCode {
	
	File file;
	BufferedWriter out = null;

	public SaveCode(String nextCode) {
		file = new File("Verification Code.dat");
		try {
			//FileInputStream第二个参数为true表示是追加内容
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
			out.write(nextCode+"\n");
		} catch (FileNotFoundException e) {
			try {
				FileWriter writer = new FileWriter(file);
				writer.write("");
				writer.close();
				System.out.println("请再次运行");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
