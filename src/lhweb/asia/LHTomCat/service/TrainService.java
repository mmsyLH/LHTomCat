package lhweb.asia.LHTomCat.service;

import lhweb.asia.LHTomCat.dao.TrainDao;
import lhweb.asia.LHTomCat.model.Page;
import lhweb.asia.LHTomCat.model.TrainNumber;
import lhweb.asia.LHTomCat.model.TrainStation;

import java.util.List;

/**
 * 列车服务
 * 处理和列车相关的业务
 *
 * @author 罗汉
 * @date 2024/02/28
 */
public class TrainService {
    private TrainDao trainDao;

    public TrainService() {
        trainDao = new TrainDao();
    }

    public static void main(String[] args) {
        TrainService trainService = new TrainService();
        Page<TrainStation> page = trainService.pageByName(1, 5, "");
        System.out.println(page);
    }
    /**
     * 按名称分页
     *
     * @param pageNo   页面
     * @param pageSize 页面大小
     * @param name     名字。
     * @return {@link Page}
     */
    public Page<TrainStation> pageByName(int pageNo,int pageSize,String name){
        //创建一个page对象 然后填充属性
        //第一个参数
        Page page = new Page();
        page.setPageNo(pageNo);
        //第二个参数
        page.setPageSize(pageSize);
        //第三个参数 总共多少行
        int totalRows = trainDao.getTotalRowsByName(name);
        page.setTotalRow(totalRows);
        //第四个参数 pageTotalCount
        // 比如 6 2 =》 6 / 2 = 3
        // 比如 5 2 =》 5 / 2 = 2
        int pageTotalCount = totalRows / pageSize;
        if (totalRows%pageSize>0){
            pageTotalCount=pageTotalCount+1;
        }
        page.setPageTotalCount(pageTotalCount);

        //第五个参数
        // 验证: pageNo = 1 pageSize = 3 => begin =0
        // 验证: pageNo = 3 pageSize = 2 => begin =4
        int begin=(pageNo-1)*pageSize;
        List<TrainStation> items = trainDao.getPageItemsByName(begin, pageSize, name);
        page.setItems(items);
        return page;
    }

    /**
     * 所有车次按站号排列
     * 查找所有车次
     *
     * @param startStationId 起始站编号
     * @param endStationId   终点站编号
     * @return {@link List}<{@link TrainNumber}>
     */
    public List<TrainNumber> fAllTrainNumberBySTAndET(String startStationId, String endStationId) {
        return trainDao.fAllTrainNumberBySTAndET(startStationId, endStationId);
    }

    /**
     * 搜索站点名称
     *
     * @param stationName 车站名字
     * @return {@link List}<{@link TrainStation}>
     */
    public List<TrainStation> searchStationLikeName(String stationName) {
        return trainDao.searchStationLikeName(stationName);
    }
}
