package lhweb.asia.LHTomCat.dao;


import lhweb.asia.LHTomCat.model.TrainNumber;
import lhweb.asia.LHTomCat.model.TrainStation;
import lhweb.asia.LHTomCat.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户DAO
 *
 * @author 罗汉
 * @date 2024/02/26
 */
public class TrainDao {

    private Connection conn = null;
    private PreparedStatement pstat = null;
    private ResultSet res = null;

    public static void main(String[] args) {
        TrainDao trainDao = new TrainDao();
        int num = trainDao.getTotalRowsByName("");
        System.out.println("num: " + num);
        List<TrainStation> pageItemsByName = trainDao.getPageItemsByName(0, 3, "");
        System.out.println(pageItemsByName);

        //更新
    }
    /**
     * 根据传入的初始页和需要展示的条数获取当前页要显示的数据
     *
     * @param stationName 车站名字
     * @param begin       开始
     * @param pageSize    页面大小
     * @return {@link List}<{@link TrainStation}>
     */
    public List<TrainStation> getPageItemsByName(int begin,int pageSize,String stationName) {
        TrainStation trainStation;
        List<TrainStation> trainStations = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            String selectQuery = "SELECT `stationid`,`stationpy`,`stationinfo` FROM train_station WHERE stationid LIKE ?  limit ?,?";
            pstat = conn.prepareStatement(selectQuery);
            pstat.setInt(2, begin);
            pstat.setInt(3, pageSize);
            pstat.setString(1, "%" + stationName + "%");
            res = pstat.executeQuery();
            // 处理查询结果
            while (res.next()) {
                trainStation = new TrainStation();
                trainStation.setStationid(res.getString("stationid"));
                trainStation.setStationinfo(res.getString("stationpy"));
                trainStation.setStationpy(res.getString("stationinfo"));
                trainStations.add(trainStation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.close(conn, pstat, res);
        }

        return trainStations;
    }

    /**
     * 按名称获取行总数
     *
     * @return int 总行数
     */
    public int getTotalRowsByName(String name) {
        int totalRows = 0;
        String selectQuery = "select COUNT(*) from train_station WHERE stationid LIKE ?";
        try {
            conn = JDBCUtils.getConnection();
            pstat = conn.prepareStatement(selectQuery);
            pstat.setString(1, "%"+name+"%");
            res = pstat.executeQuery();
            if (res.next()) {
                totalRows = res.getInt(1); // 获取结果集中的第一列数据
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.close(conn, pstat, res);
        }
        return totalRows;
    }

    /**
     * 更新车站信息
     *
     * @param trainStation 火车站
     * @return int 0表示没有执行更新 -1表示更新过程中出错
     */
    public int updateStation(TrainStation trainStation) {
        String selectQuery = "UPDATE train_station set `stationid` =?,`stationpy`=?,`stationinfo`=? where stationid=?";
        int resNum = 0;
        try {
            conn = JDBCUtils.getConnection();
            pstat = conn.prepareStatement(selectQuery);
            pstat.setString(1, trainStation.getStationid());
            pstat.setString(2, trainStation.getStationpy());
            pstat.setString(3,  trainStation.getStationinfo());
            pstat.setString(4,  trainStation.getStationid());
            resNum = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.close(conn, pstat, res);
        }
        return resNum;
    }

    /**
     * 所有车次按站号排列
     * 查找所有车次
     *
     * @param startStationId 起始站编号
     * @param endStationId   终点站编号
     * @return {@link List}<{@link TrainNumber}>
     */
    public List<TrainNumber> fAllTrainNumberBySTAndET(String startStationId,String endStationId) {
        TrainNumber trainNumber;
        List<TrainNumber> trainNumbers = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            String selectQuery = "SELECT `number`,`starttime`,`endtime`,`startstationid`,`endstationid`,`num`,`money`,`ntid`,`info` " +
                    "FROM train_number " +
                    "WHERE startstationid LIKE ? AND endstationid LIKE ?";
            pstat = conn.prepareStatement(selectQuery);
            pstat.setString(1, "%" + startStationId + "%");
            pstat.setString(2, "%" + endStationId + "%");
            res = pstat.executeQuery();
            // 处理查询结果
            while (res.next()) {
                trainNumber = new TrainNumber();
                trainNumber.setNumber(res.getString("number"));
                trainNumber.setStarttime(res.getDate("starttime"));
                trainNumber.setEndtime(res.getDate("endtime"));
                trainNumber.setStartstationid(res.getString("startstationid"));
                trainNumber.setEndstationid(res.getString("endstationid"));
                trainNumber.setNum(res.getInt("num"));
                trainNumber.setMoney(res.getInt("money"));
                trainNumber.setNtid(res.getInt("ntid"));
                trainNumber.setInfo(res.getString("info"));
                trainNumbers.add(trainNumber);
            }

        } catch (SQLException e) {
            // TODO: 处理异常
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.close(conn, pstat, res);
        }

        return trainNumbers;
    }

    /**
     * 根据名称搜索车站
     *
     * @param stationName 车站名字
     * @return {@link List}<{@link TrainStation}>
     */
    public List<TrainStation> searchStationLikeName(String stationName) {
        TrainStation trainStation;
        List<TrainStation> trainStations = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            String selectQuery = "SELECT `stationid`,`stationpy`,`stationinfo` FROM train_station WHERE stationid LIKE ?";
            pstat = conn.prepareStatement(selectQuery);
            pstat.setString(1, "%" + stationName + "%");
            res = pstat.executeQuery();
            // 处理查询结果
            while (res.next()) {
                trainStation = new TrainStation();
                trainStation.setStationid(res.getString("stationid"));
                trainStation.setStationinfo(res.getString("stationpy"));
                trainStation.setStationpy(res.getString("stationinfo"));
                trainStations.add(trainStation);
            }

        } catch (SQLException e) {
            // TODO: 处理异常
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.close(conn, pstat, res);
        }

        return trainStations;
    }
}
