package com.motionapps.GSYSocial.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.dao.FileDao;
import com.motionapps.GSYSocial.dao.vo.FileVO;
import com.motionapps.GSYSocial.util.Constants;


@Controller
@Path("/file")
public class FileController {
	
	@Autowired
	FileDao fileDao;
	

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(	@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) 
	{
		String fileId=UUID.randomUUID().toString();
		String newFileName=fileId+"-"+fileDetail.getFileName();
		String uploadedFileLocation = Constants.fileLocation + newFileName;
		try{
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);
		

			FileVO responseFileVO=new FileVO();
			responseFileVO.setFileUrl(Constants.fileUrl+newFileName);
			return Response.status(200).entity(responseFileVO).type(MediaType.APPLICATION_JSON).build();
		}
		catch(Exception e){
			return Response.status(400).build();
		}
	}
	
	@GET
	@Path("/download/{fileName}")
	public Response downloadFile(@PathParam("fileName") String fileName)
	{
		try
		{
		File file =new File(Constants.fileLocation+fileName);
	    ResponseBuilder builder = Response.ok((Object)file);
	    builder.header("Content-Disposition", "attachment; filename=" + fileName);
	    return builder.build();
		}
		catch(Exception e)
		{
			return Response.status(404).build();
		}
		

	}
	
	@GET
	@Path("/stream/{fileName}")
	@Produces("video/mp4")
	public Response streamFile(@PathParam("fileName") String fileName)
	{

		try
		{
		final File asset =new File(Constants.fileLocation+fileName);
		
		StreamingOutput streamer=new StreamingOutput() {
			
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
                final FileChannel inputChannel = new FileInputStream(asset).getChannel();
                final WritableByteChannel outputChannel = Channels.newChannel(output);
                try {
                    inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                } finally {
                    // closing the channels
                    inputChannel.close();
                    outputChannel.close();
                }
            }
        };
        return Response.ok(streamer,MediaType.MULTIPART_FORM_DATA ).status(200).header(HttpHeaders.CONTENT_LENGTH, asset.length()).build();
		}
		catch(Exception e)
		{
			return Response.status(404).build();
		}
		

	}
	
//	@POST
//	@Path("/delete")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response deleteFile(FileVO fileVO)
//	{
//		try
//		{
//		String fileURL=fileVO.getFileUrl();
//		Long abc=fileDao.deleteFile(fileURL);
//		if(abc==1)
//			return Response.ok().build();
//		else
//			return Response.status(404).build();
//		}
//		catch(Exception e)
//		{
//			return Response.status(404).build();
//		}
//
//	}
	
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

			try {
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	

}
