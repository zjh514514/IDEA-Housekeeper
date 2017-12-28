package housekeeper.service.impl;

import housekeeper.dao.CardDao;
import housekeeper.dao.MemberDao;
import housekeeper.entities.Card;
import housekeeper.entities.Member;
import housekeeper.service.CardService;
import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao cardDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OperatorService operatorService;

    @Autowired
    private Card card;
    @Autowired
    private Member member;

    @Override
    public String addCard(String name, String number, Double money, String remark, Integer memberId) {
        try {
            if (remark == null)
                remark = "";
            if (money == null)
                money = 0.0;
            if (name != null && number != null && memberId != null && memberDao.queryById(memberId).size() != 0) {
                member.setMemberId(memberId);
                card.setCardName(name);
                card.setCardNumber(number);
                card.setMember(member);
                card.setMoney(money);
                card.setRemark(remark);
                cardDao.save(card);
                return "200";
            } else {
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }
    }

    @Override
    public String delete(Integer id) {
        return operatorService.delete(id, cardDao.queryById(id).size(), "c");
    }

    @Override
    public String updateCard(String name, String number, Double money, String remark, Integer id) {
        try {
            if (money == null)
                money = 0.0;
            if (remark == null)
                remark = "";
            if (name == null)
                name = "";
            if (number == null)
                number = "";
            if (id != null && id > 0 && cardDao.queryById(id).size() != 0) {
                card.setCardId(id);
                card.setCardName(name);
                card.setCardNumber(number);
                card.setMoney(money);
                card.setRemark(remark);
                cardDao.update(card);
                return "200";
            } else {
                return "201";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "202";
        }
    }

    @Override
    public List queryByMember(Integer memberId) {
        if (memberId != null) {
            return cardDao.queryByMember(memberId);
        } else {
            return new ArrayList();
        }
    }

    @Override
    public List queryById(Integer id) {
        if (id != null && id > 0) {
            return cardDao.queryById(id);
        } else {
            return new ArrayList();
        }
    }

}
