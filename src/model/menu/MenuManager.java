package model.menu;

public class MenuManager {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void showStartMenu() {
        System.out.println(ANSI_CYAN + "\n----------MENU MÁY CHỦ----------\n" +
                "1.ĐĂNG KÍ\n" +
                "2.ĐĂNG NHẬP\n" +
                "0.THOÁT\n" +
                "Mời nhập lựa chọn của bạn:" + ANSI_RESET);
    }

    public static void showMainMenu() {
        System.out.println(ANSI_CYAN + "\n------------MENU CHÍNH------------\n" +
                "1.QUẢN LÝ TÀI KHOẢN NGƯỜI CHƠI\n" +
                "2.QUẢN LÝ ĐỒ ĂN\n" +
                "0.ĐĂNG XUẤT\n"+
                "Mời nhập lựa chọn của bạn:" + ANSI_RESET);
    }

    public static void showMenuFood() {
        System.out.println(ANSI_CYAN + "\n-----------QUẢN LÝ ĐỒ ĂN-----------\n" +
                "1.XEM DANH SÁCH ĐỒ ĂN\n" +
                "2.THÊM ĐỒ ĂN\n" +
                "3.SỬA ĐỒ ĂN\n" +
                "4.XÓA ĐỒ ĂN\n" +
                "0.THOÁT\n"+
                "Mời nhập lựa chọn của bạn:" + ANSI_RESET);
    }

    public static void showMenuPlayerAccount() {
        System.out.println(ANSI_CYAN + "\n------QUẢN LÝ TÀI KHOẢN NGƯỜI CHƠI------\n" +
                "1.XEM DANH SÁCH NGƯỜI CHƠI\n" +
                "2.ĐĂNG KÝ\n" +
                "3.NẠP TIỀN\n" +
                "4.XÓA TÀI KHOẢN\n" +
                "0.THOÁT\n"+
                "Mời nhập lựa chọn của bạn:" + ANSI_RESET);
    }
}
