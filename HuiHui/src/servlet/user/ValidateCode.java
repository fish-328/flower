package servlet.user;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/before_validateCode")
public class ValidateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//验证码从数组code中随机挑选字符（阿拉伯数字+大小写字母）
	private char code[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
			'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','0', '1','2', 
			'3', '4', '5', '6', '7', '8', '9' };
	
	//设置图片的长和宽
	private static final int WIDTH = 50;
	private static final int HEIGHT = 20;
	
	//设置验证码的字符个数
	private static final int LENGTH = 4;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应报头信息
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		// 设置响应的MIME类型
		response.setContentType("image/jpeg");
		
		//创建用于显示随机验证码的图片对象image。
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		
		//Graphics对象用于绘制图片中的内容，类似于画笔的作用
		Graphics g = image.getGraphics();
		Random rd = new Random();
		
		// 设置随机的背景颜色
		g.setColor(new Color(rd.nextInt(55) + 200, rd.nextInt(55) + 200, rd
				.nextInt(55) + 200));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// 设置字体
		Font mFont = new Font("Arial", Font.TRUETYPE_FONT, 18);
		g.setFont(mFont);
		
		// 画边框
		g.setColor(Color.black);//边框颜色
		g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
		
		// 随机产生的验证码
		String result = "";
		
		//循环4次：每次从数组code中随机选择一个字符，加入随机验证码result
		for (int i = 0; i < LENGTH; ++i) 
		{
			int x = rd.nextInt(code.length);
			char c = code[x];
			result += c;
		}
		
		//将生成的验证码存入session（会话）对象中
		HttpSession session = request.getSession();
		session.setAttribute("rand", result);
		
		// 画四个验证码字符
		for (int i = 0; i < result.length(); i++) {
			//每个验证码字符均为随机颜色
			g.setColor(new Color(rd.nextInt(200), rd.nextInt(200), rd
					.nextInt(200)));
			
			//在位置（12*i+1, 16）绘制result中的第i个字符
			g.drawString(result.charAt(i) + "", 12 * i + 1, 16);
		}
		
		// 随机产生2个干扰线
		for (int i = 0; i < 2; i++) {
			g.setColor(new Color(rd.nextInt(200), rd.nextInt(200), rd
					.nextInt(200)));
			//干扰线的起点坐标
			int x1 = rd.nextInt(WIDTH);
			int y1 = rd.nextInt(HEIGHT);
			
			//干扰线的终点坐标
			int x2 = rd.nextInt(WIDTH);
			int y2 = rd.nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 释放图形资源
		g.dispose();
		
		try {
			// 获取用于在Web页面中输出的输出流对象os
			OutputStream os = response.getOutputStream();
			
			// 输出图像到页面,图片格式为JPEG
			ImageIO.write(image, "JPEG", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
