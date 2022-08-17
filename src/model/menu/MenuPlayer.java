package model.menu;

public class MenuPlayer {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void showMenuPlayer(){
        System.out.println(ANSI_CYAN + "\n----------MENU MÁY CHƠI----------\n" +
                "1.XEM THÔNG TIN TÀI KHOẢN\n" +
                "2.ĐỔI MẬT KHẨU\n" +
                "3.MENU ĐỒ ĂN\n" +
                "4.ORDER ĐỒ ĂN\n" +
                "0.ĐĂNG XUẤT\n" +
                "MỜI NHẬP LỰA CHỌN CỦA BẠN:" + ANSI_RESET);
    }

}
