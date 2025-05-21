import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 用户实体类
class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

// 用户服务类 - 处理用户相关业务逻辑
class UserService {
    private Map<String, User> users = new HashMap<>();

    // 注册新用户
    public boolean registerUser(String username, String password, String email) {
        if (users.containsKey(username)) {
            System.out.println("用户名已存在！");
            return false;
        }
        User user = new User(username, password, email);
        users.put(username, user);
        System.out.println("注册成功！");
        return true;
    }

    // 用户登录验证
    public User login(String username, String password) {
        User user = users.get(username);
        if (user == null) {
            System.out.println("用户不存在！");
            return null;
        }
        if (!user.getPassword().equals(password)) {
            System.out.println("密码错误！");
            return null;
        }
        System.out.println("登录成功！欢迎，" + user.getUsername());
        return user;
    }

    // 显示所有用户（仅用于演示）
    public void displayAllUsers() {
        System.out.println("所有注册用户：");
        for (User user : users.values()) {
            System.out.println("- " + user.getUsername() + " (" + user.getEmail() + ")");
        }
    }
}

// 主程序 - 控制台交互界面
public class LoginSystem {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        // 初始化测试用户
        userService.registerUser("admin", "admin123", "admin@example.com");

        while (true) {
            System.out.println("\n===== 用户登录系统 =====");
            System.out.println("1. 登录");
            System.out.println("2. 注册");
            System.out.println("3. 显示所有用户");
            System.out.println("4. 退出");
            System.out.print("请选择操作：");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    System.out.print("用户名：");
                    String loginUsername = scanner.nextLine();
                    System.out.print("密码：");
                    String loginPassword = scanner.nextLine();
                    User loggedInUser = userService.login(loginUsername, loginPassword);
                    if (loggedInUser != null) {
                        // 登录成功后可以添加更多功能
                        System.out.println("用户信息：" + loggedInUser.getEmail());
                    }
                    break;
                case 2:
                    System.out.print("新用户名：");
                    String regUsername = scanner.nextLine();
                    System.out.print("密码：");
                    String regPassword = scanner.nextLine();
                    System.out.print("邮箱：");
                    String email = scanner.nextLine();
                    userService.registerUser(regUsername, regPassword, email);
                    break;
                case 3:
                    userService.displayAllUsers();
                    break;
                case 4:
                    System.out.println("感谢使用系统，再见！");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }
}