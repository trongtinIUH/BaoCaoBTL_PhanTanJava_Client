package gui;
import javax.swing.*;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class App_Karaoke4T extends JFrame implements AppKaraoke_Remote {
	
    private static final long serialVersionUID = 1L;
    private  JLabel label;
    private  JProgressBar progressBar;

	public App_Karaoke4T() {
		label = new JLabel();
        progressBar = new JProgressBar();
    }

    public static void main(String[] args) {
    	 try {
             // Getting the registry
             Registry registry = LocateRegistry.getRegistry(null);

             // Looking up the registry for the remote object
             AppKaraoke_Remote stub = (AppKaraoke_Remote) registry.lookup("App_Karaoke4T");

             // Calling the remote method using the obtained object
             stub.runApp();

             System.out.println("Remote method invoked");

             // Run the main method of App_Karaoke4T
             App_Karaoke4T.main(args);
         } catch (Exception e) {
             System.err.println("Client exception: " + e.toString());
             e.printStackTrace();
         }
    }

	@Override
	public void runApp() throws RemoteException {
		 // Tạo một cửa sổ mới
        JWindow window = new JWindow();
        window.setLayout(new BorderLayout());

        // Tạo một nhãn mới
        label = new JLabel();

        // Tạo một biểu tượng mới với hình ảnh của bạn
        ImageIcon hinhgt = new ImageIcon("./image/hinh_trangdangnhap.jpg");
		Image image = hinhgt.getImage();
		Image newImage = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
		hinhgt = new ImageIcon(newImage);

        // Thiết lập biểu tượng cho nhãn
		label.setIcon(hinhgt);

        // Thêm nhãn vào cửa sổ
        window.getContentPane().add(label, BorderLayout.CENTER);

        // Tạo thanh tiến trình
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        window.getContentPane().add(progressBar, BorderLayout.SOUTH);

        // Đặt kích thước và vị trí của cửa sổ
        window.setBounds(450, 150, 400, 400);
        window.setVisible(true);

        // Cập nhật thanh tiến trình
        for (int i = 0; i <= 100; i++) {
            final int progress = i;
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        progressBar.setValue(progress);
                    }
                });
                Thread.sleep(30); // Đợi 70ms trước khi cập nhật tiếp
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Ẩn cửa sổ sau khi thời gian chờ kết thúc
        window.setVisible(false);

        // Đóng cửa sổ khởi động
        window.dispose();

        // Tạo cửa sổ chính của ứng dụng
        GD_TrangDangNhap trangDangNhap = new GD_TrangDangNhap();
		trangDangNhap.setVisible(true);
	}
}
