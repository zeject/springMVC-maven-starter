package com.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import net.sf.json.JSONObject;

public class CommonUtil {

	private static Log logger = LogFactory.getLog(CommonUtil.class);

	public final static String ipay = "^(0|([1-9]{1}\\d{0,7}))(\\.\\d{1,2})*$";// 支付宝支付正则,一分与1亿之间
	public final static String yzbm = "^[a-zA-Z0-9 ]{3,12}$";// 邮政编码
	public final static String yls = "^\\d+((\\.?\\d+)|(\\d*))$";// 有理数
	public final static String zs = "^[0-9]{1,20}$";// 整数
	public final static String py = "^[A-Za-z]+$";// 拼音
	public final static String mobile_phone = "^1[34578]\\d{9}$";// 手机号
	public final static String email = "^([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";// 邮箱
	public final static String id = "^\\d{1,10}";

	public static final String typeJSON = "application/json";

	/*
	 * public static void erweima(String url) throws IOException,
	 * WriterException { int width = 300; int height = 300; String format =
	 * "gif"; Hashtable hints = new Hashtable();
	 * hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); BitMatrix bitMatrix =
	 * new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height,
	 * hints); File outputFile = new File("D:/new.gif");
	 * MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile); }
	 */

	/**
	 * 获得指定位数的随机数
	 * 
	 * @param num
	 *            位数
	 * @return
	 */
	public static String getRandom(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append((int) (Math.random() * 9) + 1);
		}
		return sb.toString();
	}

	public static String getUrlShort(String longUrl) {
		String url = "";
		try {
			String doc = Jsoup.connect("http://api.weibo.com/2/short_url/shorten.json").data("source", "1681459862")
					.data("url_long", longUrl).ignoreContentType(true).timeout(300000).post().text();
			org.json.JSONObject obj = new org.json.JSONObject(doc.toString());
			org.json.JSONArray o = obj.getJSONArray("urls");
			org.json.JSONObject a = o.getJSONObject(0);
			url = a.getString("url_short");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("", e);
			e.printStackTrace();
		}
		if (url.equals("")) {
			url = getUrlShort(longUrl);
		}
		return url;
	}

	/**
	 * 填充
	 * 
	 * @param id
	 *            字符串
	 * @param t
	 *            填充成什么
	 * @param length
	 *            长度
	 * @return
	 */
	public static String tc(String id, String t, int length) {
		String str = id;
		String tc = "";
		int n = str.length();
		n = length - n;
		for (int i = 0; i < n; i++) {
			tc = t + tc;
		}
		str = tc + str;
		return str;
	}

	/**
	 * 按逗号分割数组
	 * 
	 * @param strs
	 * @return
	 */
	public static String join(String[] strs) {
		return join(strs, ",");
	}

	/**
	 * 数组连接方法,按照指定字符串连接数组
	 * 
	 * @param strs
	 *            数组
	 * @param f
	 *            连接字符串
	 * @return 连接后的新字符串
	 */
	public static String join(String[] strs, String f) {
		if (strs.length < 1) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String str : strs) {
			sb.append(str).append(f);
		}
		return sb.substring(0, sb.length() - f.length());
	}

	/**
	 * 处理字符串 不能为空, 逗号 结尾 ,多个逗号 String str =
	 * ",,,,,,,,,,,,,,a,,,,,,,,s,,,d,,,,,,,,";
	 * 
	 * @param str
	 * @return a,s,d
	 */
	public static String getNotComma(String str) {
		String[] strs = str.split(",");
		String returnStr = "";
		for (int i = 0; i < strs.length; i++) {
			if (!strs[i].trim().equals("")) {
				returnStr += strs[i] + ",";
			}
		}
		returnStr = returnStr.substring(0, returnStr.length() - 1);
		return returnStr;
	}

	/**
	 * sql in(?,?,?) 传参返回?字符串
	 * 
	 * @param length
	 *            数组长度
	 * @return (?,?,?)
	 */
	public static String getWen(int length) {
		if (length < 1) {
			return "(null)";
		}
		String str = "(";
		for (int i = 0; i < length; i++) {
			str = str + "?,";
		}
		str = str.substring(0, str.length() - 1) + ")";
		return str;
	}

	/**
	 * MD5加密算法
	 * 
	 * @param args
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	// asie-a-a-title-a-a-userid-a-a-username-a-a-date-a-a-0-a-a-0
	/**
	 * 搜索列表参数构造返回 1:asie 2:title 3:userid 4:username 5:date
	 * 
	 * @param urlcs
	 * @return
	 */
	public static Map urlcs(String urlcs) {
		Map<String, String> map = new HashMap<String, String>();
		String[] css = urlcs.split("-a-a-");
		if (css.length < 7) {
			map.put("error", "500");
			return map;
		}
		for (int i = 0; i < css.length; i++) {
			if (css[i].equals("0")) {
				css[i] = "";
			}
		}
		/*
		 * String th = request.getParameter("th"); if(th!=null &&
		 * !th.equals("")){ String[] ths = th.split("-a-a-");
		 * css[Integer.valueOf(ths[1])-1] = ths[0].trim(); }
		 */
		map.put("asie", css[0]);
		map.put("pageNumber", css[1]);
		/*
		 * StringBuffer sb = new StringBuffer(); if(th!=null && !th.equals("")){
		 * for(int i = 0; i < css.length; i++){ if (css[i].equals("")) { css[i]
		 * = "0"; } sb.append(css[i]).append("-a-a-"); } sb.substring(0,
		 * sb.length()-5); sb.append(".htm"); } map.put("url", sb.toString());
		 */
		return map;
	}

	public static void response(HttpServletResponse response, Object obj) {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.print(obj);
				out.flush();
				out.close();
			}
		}
	}

	public static void ajaxresponse(HttpServletResponse response, Map responseMap, String type) {

	}

	/**
	 * AJAX
	 * 
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public static void ajaxresponse(HttpServletResponse response, Map responseMap) {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.print(JSONObject.fromObject(responseMap));
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * AJAX
	 * 
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public static void ajaxresponse(HttpServletResponse response, Object obj, String consumes) {
		response.setContentType(consumes + ";charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				if (consumes.toLowerCase().indexOf("json") != -1) {
					out.print(net.sf.json.JSONObject.fromObject(obj));
				} else {
					out.print(obj);
				}
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * event
	 * 
	 * @param response
	 * @param obj
	 */
	public static void eventResponse(HttpServletResponse response, Object obj) {
		response.setContentType("text/event-stream;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				// out.write("retry: 1000");
				if (obj instanceof String) {
					if (obj.toString().startsWith("retry")) {
						out.print(obj);
					}
				} else if (obj instanceof Map) {
					out.print("data:" + net.sf.json.JSONObject.fromObject(obj) + "\n\n");
				} else {
					out.print("data:" + obj + "\n\n");
				}
				out.flush();
				out.close();
			}
		}
	}

	public static String getUUID() {
		String str = java.util.UUID.randomUUID().toString();
		String s[] = str.split("-");
		String uuid = "";
		for (int i = 0; i < s.length; i++) {
			uuid += s[i];
		}
		return uuid;
	}

	/**
	 * 默认yyyy-MM-dd HH:mm:ss
	 * 
	 * @param gs
	 * @return
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd HH:mm:ss");
	}

	public static String getDate(String gs) {
		SimpleDateFormat format = new SimpleDateFormat(gs);
		Date d = new Date();
		String sendtime = format.format(d);
		return sendtime;
	}

	public static String getImgName(String path) {
		path = path.substring(path.lastIndexOf("/"));
		if (path.equals("") || path.equals("wutu.jpg") || path.lastIndexOf(".") == -1) {
			return path;
		} else {
			path = getUUID() + path.substring(path.lastIndexOf("."));
			return path;
		}
	}

	public static String sendTemplate(String content, Map<String, Object> csMap) {
		for (String key : csMap.keySet()) {
			content = content.replaceAll("\\$\\{" + key + "\\}", csMap.get(key).toString());
		}
		return content;
	}

	private static File tempPathFile;

	public static List uploadImage(HttpServletRequest request, HttpServletResponse response) {
		List listimg = new ArrayList();
		try {
			String uploadPath = request.getRealPath("/photo");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			factory.setRepository(tempPathFile);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			upload.setSizeMax(4194304);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> i = items.iterator();
			while (i.hasNext()) {
				FileItem fi = i.next();
				String fileName = fi.getName();
				if (fileName != null) {
					String kzm = fileName.substring(fileName.lastIndexOf("."));
					String uuid = CommonUtil.getUUID();
					File savedFile = new File(uploadPath, uuid + kzm);
					fi.write(savedFile);
					listimg.add(uuid + kzm);
				}
			}
		} catch (FileUploadException e) {
			logger.error("", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return listimg;
	}

	// 上传到服务器的图片文件前缀
	// private static final String FILE_PATH = "/html/file/images/";
	// 上传到upyun的图片文件前缀
	// private static final String FILE_UPYUN = "/file/images/";

	/**
	 * 上传到服务器
	 * 
	 * @param realPath
	 *            绝对路径
	 * @param fileSizeMax
	 *            上传文件大小 byte
	 * @param zz
	 *            正则(jpg|png|jpeg|gif|bmp)
	 * @param request
	 * @param upyun
	 *            是否上传到upyun true or false
	 * @return 已上传图片名字
	 */
	public static List upLoadServer(String realPath, long fileSizeMax, String zz, HttpServletRequest request,
			boolean upyun) {
		List imgList = new ArrayList();
		BufferedInputStream in = null;
		FileOutputStream a = null;
		BufferedOutputStream output = null;
		try {
			if ((realPath).length() == 0) {
				return null;
			}
			// 判断路径是否存在，不存在则创建
			CommonUtil.checkCreatDir(realPath);
			File dir = new File(realPath);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}
			if (ServletFileUpload.isMultipartContent(request)) {
				DiskFileItemFactory dff = new DiskFileItemFactory();
				dff.setRepository(dir);
				dff.setSizeThreshold(1024000);
				ServletFileUpload sfu = new ServletFileUpload(dff);
				if (fileSizeMax != -1) {
					sfu.setFileSizeMax(fileSizeMax);// 上传图片的大小
				}
				FileItemIterator fii = sfu.getItemIterator(request);
				String title = ""; // 图片标题
				String url = ""; // 图片地址
				String fileName = "";
				String state = "SUCCESS";
				while (fii.hasNext()) {
					FileItemStream fis = fii.next();
					if (!fis.isFormField() && fis.getName().length() > 0) {
						fileName = fis.getName();
						Pattern reg = Pattern.compile("[.]" + zz + "$");
						Matcher matcher = reg.matcher(fileName.toLowerCase());
						if (!matcher.find()) {
							state = "文件类型不允许！";
							break;
						}
						String[] hzm = fis.getName().split("\\.");
						// String ram=System.currentTimeMillis()+"";
						String ram = CommonUtil.getUUID() + "";
						String m = ram + "." + hzm[hzm.length - 1];// 图片
						url = realPath + System.getProperty("file.separator") + m;
						in = new BufferedInputStream(fis.openStream());// 获得文件输入流
						a = new FileOutputStream(new File(url));
						output = new BufferedOutputStream(a);
						Streams.copy(in, output, true);// 开始把文件写到你指定的上传文件夹
						imgList.add(m);// 增加到list
						// 上传到upyun
						if (upyun) {
							boolean bb = UpyunUtil.writeFile(realPath + m, realPath + m);
						}
					} else {
						String fname = fis.getFieldName();
						if (fname.indexOf("pictitle") != -1) {
							in = new BufferedInputStream(fis.openStream());
							byte c[] = new byte[10];
							int n = 0;
							while ((n = in.read(c)) != -1) {
								title = new String(c, 0, n);
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					a.close();
					output.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return imgList;
	}

	/**
	 * 创建目录
	 * 
	 * @param dirPath
	 */
	public static void checkCreatDir(String dirPath) { // 目录是否存在
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * error
	 * 
	 * @return
	 */
	public static String e(Exception e) {
		String str = e.toString() + "\r\n";
		for (int i = 0; i < e.getStackTrace().length; i++) {
			str += e.getStackTrace()[i] + "\r\n";
		}
		return str;
	}

	/**
	 * 对图片裁剪，并把裁剪完的新图片保存 (Server)
	 * 
	 * @param name
	 *            图片名
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param num
	 * @param srcpath
	 *            图片路径
	 * @param subpath
	 *            裁剪后的保存路径
	 * @throws IOException
	 */
	public static void cutServer(String x1, String y1, String w1, String h1, String srcpath, String subpath) {
		int x = (int) Double.parseDouble(x1);
		int y = (int) Double.parseDouble(y1);
		int w = (int) Double.parseDouble(w1);
		int h = (int) Double.parseDouble(h1);
		String kzm = srcpath.split("\\.")[srcpath.split("\\.").length - 1];
		FileInputStream is = null;
		ImageInputStream iis = null;

		try {
			// 读取图片文件
			is = new FileInputStream(srcpath);

			/**
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
			 * 参数：formatName - 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。
			 */
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(kzm);
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);

			/**
			 * iis:读取源.true:只向前搜索.将它标记为 ‘只向前搜索’。 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许
			 * reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
			reader.setInput(iis, true);

			/**
			 * <p>
			 * 描述如何对流进行解码的类
			 * <p>
			 * .用于指定如何在输入时从 Java Image I/O 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其
			 * ImageReader 实现的 getDefaultReadParam 方法中返回 ImageReadParam 的实例。
			 */
			ImageReadParam param = reader.getDefaultReadParam();

			/**
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */
			Rectangle rect = new Rectangle(x, y, w, h);

			// 提供一个 BufferedImage，将其用作解码像素数据的目标。
			param.setSourceRegion(rect);

			/**
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
			 * BufferedImage 返回。
			 */
			BufferedImage bi = reader.read(0, param);

			// 保存新图片
			// ImageIO.write(bi, "jpg", new File(subpath + num + ".jpg"));
			CommonUtil.checkCreatDir(subpath);
			ImageIO.write(bi, kzm, new File(subpath));
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (iis != null)
				try {
					iis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	/**
	 * 获取IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String[] ips = ip.split(",");
		if (ips.length > 1) {
			ip = ips[0];
		}
		return ip;
	}
}
