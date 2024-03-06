package lhweb.asia.LHTomCat.servlet;

import com.google.gson.Gson;
import lhweb.asia.LHTomCat.annotation.WebServlet;
import lhweb.asia.LHTomCat.common.Result;
import lhweb.asia.LHTomCat.http.*;
import lhweb.asia.LHTomCat.model.Page;
import lhweb.asia.LHTomCat.model.TrainNumber;
import lhweb.asia.LHTomCat.model.TrainStation;
import lhweb.asia.LHTomCat.service.TrainService;
import lhweb.asia.LHTomCat.utils.DataUtils;

import java.util.List;

/**
 * 用户servlet
 * 这里处理和用户相关的信息
 *
 * @author 罗汉
 * @date 2024/02/26
 */
@WebServlet()
public class TrainServlet extends LhHttpServlet {
    private TrainService trainService;// 用户服务类
    private Gson gson;// 谷歌的解析json的工具类

    public TrainServlet() {
        gson = new Gson();
        trainService = new TrainService();
    }

    @Override
    public void doGet(LhRequest req, LhResponse resp) {
        String action = req.getParameter("action");
        if ("selectTrain".equals(action)) {// 检查请求的操作是查询车次
            selectTrain(req, resp);
        } else if ("searchStation".equals(action)) {// 检查请求的操作是查询车站
            searchStationLikeName(req, resp);
        } else if ("pageByName".equals(action)) {// 检查请求的操作是分页查询车站
            pageByName(req, resp);
        } else if ("delete".equals(action)) {
            delete(req, resp);
        } else if ("update".equals(action)) {
            update(req, resp);
        } else if ("add".equals(action)) {
            add(req, resp);
        } else {
            // 返回错误信息，说明该类没有请求的操作方法
            resp.writeToJson(gson.toJson(Result.error(getClass().getSimpleName() + "没有 " + req.getParameter("action") + " 方法")));
        }
    }

    @Override
    public void doPost(LhRequest request, LhResponse response) {
        this.doGet(request, response);
    }

    /**
     * 添加车站
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    private void add(LhRequest req, LhResponse resp) {
        String stationid = req.getParameter("stationid");
        String stationpy = req.getParameter("stationpy");
        String stationinfo = req.getParameter("stationinfo");

        // 检查stationid是否为null或空
        if (stationid == null || stationpy == null || stationinfo == null || stationpy.isEmpty() || stationinfo.isEmpty() || stationid.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("添加失败，上传的属性至少有一个为空")));
            return;
        }
        TrainStation trainStation = new TrainStation();
        trainStation.setStationinfo(DataUtils.decodeChinese(stationinfo));
        trainStation.setStationid(DataUtils.decodeChinese(stationid));
        trainStation.setStationpy(DataUtils.decodeChinese(stationpy));
        boolean res = trainService.add(trainStation);
        String jsonResponse = res ? gson.toJson(Result.success("添加成功")) : gson.toJson(Result.error("添加失败"));

        resp.writeToJson(jsonResponse);
    }

    /**
     * 更新车站
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    private void update(LhRequest req, LhResponse resp) {
        String stationid = req.getParameter("stationid");
        String stationpy = req.getParameter("stationpy");
        String stationinfo = req.getParameter("stationinfo");

        // 检查stationid是否为null或空
        if (stationid == null || stationpy == null || stationinfo == null || stationpy.isEmpty() || stationinfo.isEmpty() || stationid.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("更新失败，上传的属性至少有一个为空")));
            return;
        }
        TrainStation trainStation = new TrainStation();
        trainStation.setStationinfo(DataUtils.decodeChinese(stationinfo));
        trainStation.setStationid(DataUtils.decodeChinese(stationid));
        trainStation.setStationpy(DataUtils.decodeChinese(stationpy));
        boolean res = trainService.update(trainStation);
        String jsonResponse = res ? gson.toJson(Result.success("更新成功")) : gson.toJson(Result.error("更新失败"));

        resp.writeToJson(jsonResponse);
    }

    /**
     * 删除
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    private void delete(LhRequest req, LhResponse resp) {
        String stationid = req.getParameter("stationid");

        // 检查stationid是否为null或空
        if (stationid == null || stationid.isEmpty()) {
            resp.writeToJson(gson.toJson(Result.error("需要删除的stationid缺失")));
            return;
        }
        boolean res = trainService.delete(stationid);
        String jsonResponse = res ? gson.toJson(Result.success("删除成功")) : gson.toJson(Result.error("删除失败"));

        resp.writeToJson(jsonResponse);
    }


    /**
     * 按名称分页查询
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    private void pageByName(LhRequest req, LhResponse resp) {
        String pageNo = req.getParameter("pageNo");
        String pageName = req.getParameter("pageName");
        if (null == pageName) {
            pageName = "";
        }
        String pageSize = req.getParameter("pageSize");
        // 调用trainService去模糊查询车站
        Page<TrainStation> page = trainService.pageByName(Integer.parseInt(pageNo), Integer.parseInt(pageSize), pageName);

        // 1 fastJson 将查询结果转换为JSON字符串
        // String presJson=JSON.toJSONString(Result.success(page, "分页查询成功"));

        // 2 Gson
        String presJson = gson.toJson(Result.success(page, "分页查询成功"));
        // 将JSON字符串写入响应对象中
        resp.writeToJson(presJson);
    }

    /**
     * 模糊查询车站
     *
     * @param req  要求事情
     * @param resp 分别地
     */
    private void searchStationLikeName(LhRequest req, LhResponse resp) {
        String stationName = req.getParameter("stationName");
        // 调用trainService去模糊查询车站
        List<TrainStation> trainStations = trainService.searchStationLikeName(stationName);

        // 将查询结果转换为JSON字符串
        String presJson;
        if (trainStations.size() > 0) {
            // 查询成功，返回查询结果
            presJson = gson.toJson(Result.success(trainStations, "查询成功"));
        } else {
            // 没有符合条件的车站，返回错误信息
            presJson = gson.toJson(Result.error("暂无该列车"));
        }

        // 将JSON字符串写入响应对象中
        resp.writeToJson(presJson);
    }

    /**
     * 查询车次
     *
     * @param req  请求对象，包含请求参数
     * @param resp 响应对象，用于返回结果
     */
    private void selectTrain(LhRequest req, LhResponse resp) {
        // 解码出发站点和终点站点的中文问题  防止request中解码不成功
        String startstationid = DataUtils.decodeChinese(req.getParameter("startstationid"));
        String endstationid = DataUtils.decodeChinese(req.getParameter("endstationid"));

        // 查询符合条件的火车班次
        List<TrainNumber> trainNumbers = trainService.fAllTrainNumberBySTAndET(startstationid, endstationid);

        // 将查询结果转换为JSON字符串
        String presJson;
        if (trainNumbers.size() > 0) {
            // 查询成功，返回查询结果
            presJson = gson.toJson(Result.success(trainNumbers, "查询成功"));
        } else {
            // 没有符合条件的火车班次，返回错误信息
            presJson = gson.toJson(Result.error("没有该班次列车"));
        }

        // 将JSON字符串写入响应对象中
        resp.writeToJson(presJson);
    }


    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }

}
