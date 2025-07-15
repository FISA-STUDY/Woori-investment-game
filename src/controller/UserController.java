package controller;

import model.UserDAO;
import model.domain.User;

public class UserController {

    private final UserDAO userDAO = UserDAO.getModel();

    // 회원가입 처리
    public String register(String id, String pwd) {
        boolean success = userDAO.register(id, pwd);
        return success ? "✅ 회원가입 성공!" : "❌ 이미 존재하는 아이디입니다.";
    }

    // 로그인 처리
    public String login(String id, String pwd) {
        boolean success = userDAO.login(id, pwd);
        if (success) {
            User user = userDAO.getCurrentPlayer();
            return "✅ 로그인 성공: " + user.getUName() + ", 보유 자산: " + user.getUWallet();
        } else {
            return "❌ 로그인 실패: 아이디 또는 비밀번호가 틀렸습니다.";
        }
    }

    // 현재 유저 조회
    public User getCurrentUser() {
        return userDAO.getCurrentPlayer();
    }
}
