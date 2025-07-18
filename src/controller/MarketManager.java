package controller;

import java.util.List;

import model.PortfolioDAO;
import model.UserDAO;
import model.PortfolioDAO;
import model.domain.PortFolio;
import model.domain.Stock;
import model.domain.User;

public class MarketManager {
    // 포트폴리오 가져와서 전체 수량, 가격 정리

    private static UserDAO userDAO = UserDAO.getModel();
    private static PortfolioDAO portfolioDAO = PortfolioDAO.getPortfolioDAO();

    void buyStock(Stock stock, int num) {
        boolean found = false;

        for (PortFolio portfolio : portfolioDAO.getPortFolios()) {
            if (portfolio.getSName().equals(stock.getSName())) {
                int nowPrice = (portfolio.getPPrice() * portfolio.getPAmount() + stock.getSPrice() * num)
                        / (portfolio.getPAmount() + num);
                portfolio.setPPrice(nowPrice);
                portfolio.setPAmount(portfolio.getPAmount() + num);
                portfolioDAO.updatePortfolio(portfolio);
                found = true;
                break;
            }
        }
        if (!found) {
            PortFolio pf = new PortFolio(
                    null, // pId (auto_increment)
                    stock.getSPrice(), // pPrice
                    num, // pAmount
                    userDAO.getCurrentPlayer().getUName(), // uName
                    stock.getSName(), // sName
                    (long) stock.getSId() // sId
            );

            portfolioDAO.insertPortfolio(pf);
        }
    }

    boolean sellStock(Stock stock, int num) {
        for (PortFolio portfolio : portfolioDAO.getPortFolios()) {
            if (portfolio.getSName().equals(stock.getSName())) {
                if (portfolio.getPAmount() < num) {
                    System.out.println("판매할 개수가 보유량보다 클 수 없습니다.");
                    return false;
                }

                // 수량 감소
                portfolio.setPAmount(portfolio.getPAmount() - num);
                if (portfolio.getPAmount() == 0) {
                    portfolioDAO.deletePortfolio(userDAO.getCurrentPlayer().getUName(), stock.getSName());
                }
                // 평균 단가는 매도 시 갱신하지 않음

                return true;
            }
        }
        return false; // 보유한 종목이 없음
    }

    public static int calculateTotalPortfolioValue() {
        List<PortFolio> playerPortfolios = portfolioDAO.getPortFolios();
        int totalValue = 0;

        for (PortFolio portfolio : playerPortfolios) {
            try {
                Stock currentStock = StockManager.getStockByName(portfolio.getSName());
                if (currentStock != null) {
                    totalValue += currentStock.getSPrice() * portfolio.getPAmount();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return totalValue;
    }

    public static int calculateTotalAsset() {
        User currentPlayer = userDAO.getCurrentPlayer();
        return (int) (currentPlayer.getUWallet() + calculateTotalPortfolioValue());
    }

    public static List<PortFolio> showPortfolio() {
        return portfolioDAO.getPortFolios();
    }

}