package housekeeper.test;

import housekeeper.service.CardService;
import housekeeper.service.CashInAndCashOutService;
import housekeeper.service.FamilyAndMemberService;
import housekeeper.service.ItemsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ServiceTest {

    @Resource
    private FamilyAndMemberService familyAndMemberService;
    @Resource
    private ItemsService itemsService;
    @Resource
    private CardService cardService;
    @Resource
    private CashInAndCashOutService cashInAndCashOutServoice;

    @Test
    public void test2() throws Exception {
        String time2 = "1513058904";
        Long time = new Long(time2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = format.format(time * 1000L);
        System.out.println(format.parse(date));
    }

    @Test
    public void test() {
        String date = "2015-10-18 18:23";
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            System.out.println(f.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("FAILED");
        }
    }

    /*
     * 测试FamilyAndMemberService
     */
    @Test
    public void testFamilySign() {
        System.out.println(familyAndMemberService.familySign("abc", "abc123456", "老王家"));
    }

    @Test
    public void testMemberSign() {
        System.out.println(familyAndMemberService.memberSign("cc", "cc", "cc", "cc", 1));
    }

    @Test
    public void testFamilyLogin() {
//        System.out.println(familyAndMemberService.familyLogin("abc123", "123456"));
    }

//    @Test
//    public void testMemberLogin() {
//        System.out.println(familyAndMemberService.memberLogin("zjh", ""));
//    }

    @Test
    public void testFamilyGet() {
        System.out.println(familyAndMemberService.familyGet(2));
    }

    @Test
    public void testMemberFamilyGet() {
        System.out.println(familyAndMemberService.memberFamilyGet(5));
    }

    @Test
    public void testMemberGet() {
        System.out.println(familyAndMemberService.memberGet(678));
    }

    @Test
    public void testFamilyUpdate() {
        System.out.println(familyAndMemberService.familyUpdate("cc", "cc", null));
    }

    @Test
    public void testMemberUpdate() {
        System.out.println(familyAndMemberService.memberUpdate("dd", "dd", 453.05, 1, "dd"));
    }

    @Test
    public void testMemberDelete() {
        System.out.println(familyAndMemberService.delete(2, "m"));
    }

    @Test
    public void testFamilyDelete() {
        System.out.println(familyAndMemberService.delete(1, "f"));
    }

    /**
     * 测试ItemsService
     */
    @Test
    public void testItemsAddItems() {
        System.out.println(itemsService.addItems("bb", 1));
    }

    @Test
    public void testAddSubItems() {
        System.out.println(itemsService.addSubItems("bb", 4));
    }

    @Test
    public void testDeleteItem() {
        System.out.println(itemsService.delete(18, "i"));
    }


    @Test
    public void testUpdateItem() {
        System.out.println(itemsService.updateItem("aa", 2));
    }

    @Test
    public void testUpdateSubItem() {
        System.out.println(itemsService.updateSubItem("cc", 1));
    }

    @Test
    public void testQueryItem() {
        System.out.println(itemsService.queryItem(5) == null);
    }

    @Test
    public void testQuerySubItem() {
        System.out.println(itemsService.querySubItem(1));
    }

    /**
     * ����CardService
     */
    @Test
    public void testAddCard() {
        System.out.println(cardService.addCard("dd", "dd", 84.05, "dd", null));
    }

    @Test
    public void testDeleteCard() {
        System.out.println(cardService.delete(-1));
    }

    @Test
    public void testUpdateCard() {
        System.out.println(cardService.updateCard("bb", "bb", 978.05, "bb", 5));
    }

    @Test
    public void testQueryByMember() {
        System.out.println(cardService.queryByMember(7));
    }

    @Test
    public void testQueryById() {
        List cards = cardService.queryById(null);
        System.out.println(cards);
    }

    /**
     * ����CashInAndCashOutService
     */
    @Test
    public void testAddCashIn() {
        System.out.println(cashInAndCashOutServoice.add("", "", "", 846.05, "", 6, 1, 13, 1, ""));
    }

    @Test
    public void testDeleteCashIn() {
        System.out.println(cashInAndCashOutServoice.delete(6, "ci"));
    }

    @Test
    public void testUpdateCashIn() {
        System.out.println(cashInAndCashOutServoice.update(null, "ee", "ee", 987.05, "ee", null, 1, 8, 8, ""));
    }

    @Test
    public void testQueryCashInByMember() {
        System.out.println(cashInAndCashOutServoice.queryCashInByMember(0));
    }

    @Test
    public void testQueryCashInByItem() {
        System.out.println(cashInAndCashOutServoice.queryCashInByItem(0, 0));
    }

    @Test
    public void testQueryCashInBySubItem() {
        System.out.println(cashInAndCashOutServoice.queryCashInBySubItem(null, null));
    }

    @Test
    public void testQueryCashInById() {
        System.out.println(cashInAndCashOutServoice.queryCashInById(8));
    }

    @Test
    public void testQueryByTime() throws ParseException {
        Long date = new Long("1507651200");
        Long date2 = new Long("1508428800");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(cashInAndCashOutServoice.queryByTime(3, f.parse(f.format(date * 1000L)), f.parse(f.format(date2 * 1000L)), "o"));
    }

    @Test
    public void testMemberSumCash() throws ParseException {
        Long date = new Long("1507651200");
        Long date2 = new Long("1508428800");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(cashInAndCashOutServoice.memberSumCash(3, f.parse(f.format(date * 1000L)), f.parse(f.format(date2 * 1000L)), "o"));
    }

    @Test
    public void testYearSum() {
        System.out.println(cashInAndCashOutServoice.yearSum(3, "2017"));
    }

    @Test
    public void testFamilyCashIn() {
        System.out.println(cashInAndCashOutServoice.familyCashIn(1));
    }

    @Test
    public void testFamilyCashOut() {
        System.out.println(cashInAndCashOutServoice.familyCashOut(1));
    }
}
