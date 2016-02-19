package com.motionapps.GSYSocial.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.dao.FileDao;
import com.motionapps.GSYSocial.dao.vo.FileVO;
import com.motionapps.GSYSocial.util.Constants;
import com.motionapps.GSYSocial.util.MediaStreamer;
import com.sun.org.apache.xpath.internal.operations.Bool;


@Controller
@Path("/file")
public class FileController {
	
	@Autowired
	FileDao fileDao;
	
    final int chunk_size = 1024 * 1024 ; // 1MB chunks
    Boolean status=false;
	

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
		String newFileName=fileId+"-"+URLEncoder.encode(fileDetail.getFileName());
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
	@Path("/delete/{fileName}")
	public Response deleteFile(@PathParam("fileName") String fileName)
	{
		try
		{
		File file =new File(Constants.fileLocation+fileName);
		status=file.delete();
		if(status)
			return Response.status(200).build();
		else
			return Response.status(404).build();
		}
		catch(Exception e)
		{
			return Response.status(404).build();
		}
		

	}
	
	@GET
	@Path("/stream/{fileName}")
	@Produces("video/mp4")
	public Response streamFile(@HeaderParam("Range") String range,@PathParam("fileName") String fileName)
	{

		try
		{
			String urldecodedfileName=URLDecoder.decode(fileName);
			File file =new File(Constants.fileLocation+urldecodedfileName);
			return buildStream(file, range);
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
	
	
//	  //A simple way to verify if the server supports range headers.
//    @HEAD
//    //@Produces("audio/mp3")
//    //@Produces("video/mp4")
//    public Response header() {
//        return Response.ok().status(206).header(HttpHeaders.CONTENT_LENGTH, fileUri.length()).build();
//    }
	
	 /**
     * Adapted from http://stackoverflow.com/questions/12768812/video-streaming-to-ipad-does-not-work-with-tapestry5/12829541#12829541
     *
     * @param asset Media file
     * @param range range header
     * @return Streaming output
     * @throws Exception IOException if an error occurs in streaming.
     */
    private Response buildStream(final File asset, final String range) throws Exception {
        // range not requested : Firefox, Opera, IE do not send range headers
        if (range == null) {
            StreamingOutput streamer = new StreamingOutput() {
                public void write(final OutputStream output) throws IOException, WebApplicationException {
                	System.out.println("StreamingOutput");
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
            return Response.ok(streamer).status(200).header(HttpHeaders.CONTENT_LENGTH, asset.length()).build();
        }

        String[] ranges = range.split("=")[1].split("-");
        final int from = Integer.parseInt(ranges[0]);
        /**
         * Chunk media if the range upper bound is unspecified. Chrome sends "bytes=0-"
         */
        int to = chunk_size + from;
        if (to >= asset.length()) {
            to = (int) (asset.length() - 1);
        }
        if (ranges.length == 2) {
            to = Integer.parseInt(ranges[1]);
        }

        final String responseRange = String.format("bytes %d-%d/%d", from, to, asset.length());
        final RandomAccessFile raf = new RandomAccessFile(asset, "r");
        raf.seek(from);

        final int len = to - from + 1;
        final MediaStreamer streamer = new MediaStreamer(len, raf);
        Response.ResponseBuilder res = Response.ok(streamer).status(206)
                .header("Accept-Ranges", "bytes")
                .header("Content-Range", responseRange)
                .header(HttpHeaders.CONTENT_LENGTH, streamer.getLenth())
                .header(HttpHeaders.LAST_MODIFIED, new Date(asset.lastModified()));
        return res.build();
    }

	

}
