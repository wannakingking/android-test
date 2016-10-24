package me.ctrlor.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool
	{
		public static byte[] read(InputStream inputStream) throws Exception
		{
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while( (len = inputStream.read(buffer)) != -1)
			{
				outStream.write(buffer, 0, len);
			}
			
			inputStream.close();
			return outStream.toByteArray();
		}
		
	}