package housekeeper.service.impl;

import java.util.List;

import javax.annotation.Resource;

import housekeeper.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import housekeeper.dao.CardDao;
import housekeeper.dao.MemberDao;
import housekeeper.entities.Card;
import housekeeper.entities.Member;
import housekeeper.service.CardService;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao cardDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
    private OperatorService operatorService;

	@Resource(name = "card")
	private Card card;
	@Resource(name = "member")
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
				return "SUCCESS";
			} else {
				return "FAILED";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	@Override
	public String delete(Integer id) {
		return operatorService.delete(id,cardDao.queryById(id).size(),"c");
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
			if (id != null) {
				if (id > 0 && cardDao.queryById(id).size() != 0) {
					card.setCardId(id);
					card.setCardName(name);
					card.setCardNumber(number);
					card.setMoney(money);
					card.setRemark(remark);
					cardDao.update(card);
					return "SUCCESS";
				} else {
					return "FAILED";
				}
			} else {
				return "FAILED";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}

	@Override
	public List queryByMember(Integer memberId) {
		if (memberId != null) {
			if (memberId > 0 && memberDao.queryById(memberId).size() != 0) {
				// member.setMemberId(memberId);
				List cards = cardDao.queryByMember(memberId);
				if (cards.size() != 0) {
					return cards;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public List queryById(Integer id) {
		if (id != null) {
			if (id > 0) {
				List cards = cardDao.queryById(id);
				if (cards.size() != 0) {
					return cards;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
