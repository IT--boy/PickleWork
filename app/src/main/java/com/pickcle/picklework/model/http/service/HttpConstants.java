package com.pickcle.picklework.model.http.service;

/**
 * Created by justcan on 2018/1/15.
 * 请求地址常量
 */

public class HttpConstants {
    public static final String RELEASE_URL = "https://v2.api.justcan.cn/hmp-app/";
//    public static final String RELEASE_URL = "http://192.168.0.10/hmp-app/";
//    public static final String RELEASE_URL = "http://192.168.0.160:8090/hmp-app/";    //本地
    public static final String TEST_URL = "http://www.justcan.cn:7005/hmp-app/";      //旧测试环境
//    public static final String TEST_URL = "http://local.justcan.cn/hmp-app/";               //新测试环境
//    public static final String TEST_URL = "http://192.168.0.193:8090/hmp-app/";       //本地
    public static final String LOCAL_URL = "http://192.168.0.229:8090/hmp-app/";
    public static final String YIDONG_URL = "http://www.justcan.cn:7001/hmp-app/";
    /**
     * 隐私条款地址
     */
    public static final String PRIVACY_URL = "http://hmp.justcan.cn/v2/privacy.html";
    /**
     * 服务条款地址
     */
    public static final String SERVICE_URL = "http://hmp.justcan.cn/v2/service.html";
    /**
     * 生命树功率地址
     */
    public static final String TREE_STRATEGY_URL = "https://www.baidu.com/";
    /**
     * 微信授权使用手册
     */
    public static final String WECHAT_ACCREDIT_MANUAL_URL = "http://hmp.justcan.cn/wxstep.html";

    /**
     * 授权登录
     */
    public static final String LOGIN_OAUTH = "v2/login/oauth";
    public static final String LOGIN_OAUTH_N = "201004";
    /**
     * 发送验证码
     */
    public static final String SMS_VERIFYCODE = "v2/sms/verifyCode";
    public static final String SMS_VERIFYCODE_N = "201002";
    /**
     * 手机号校验
     */
    public static final String PHONE_CHECK = "v2/phone/check";
    public static final String PHONE_CHECK_N = "201001";
    /**
     * 注册
     */
    public static final String REGISTER = "v2/register";
    public static final String REGISTER_N = "201003";
    /**
     * 修改密码
     */
    public static final String MODIFYPASSWORD = "v2/modifyPassword";
    public static final String MODIFYPASSWORD_N = "201005";
    /**
     * APP升级
     */
    public static final String SYSTEM_VERSION = "v2/system/version";
    public static final String SYSTEM_VERSION_N = "201007";
    /**
     * 第三方登录
     */
    public static final String LOGIN_THIRD = "v2/login/third";
    public static final String LOGIN_THIRD_N = "201006";
    /**
     * 第三方绑定
     */
    public static final String USER_BIND = "v2/user/bind";
    public static final String USER_BIND_N = "202001";
    /**
     * 头像上传
     */
    public static final String USER_PICTURE_UPLOAD = "v2/user/picture/upload";
    public static final String USER_PICTURE_UPLOAD_N = "202005";
    /**
     * 基本信息保存
     */
    public static final String USER_BASE_SAVE = "v2/user/base/save";
    public static final String USER_BASE_SAVE_N = "202004";
    /**
     * 微行动列表（计划）查询接口
     */
    public static final String MICRO_ACTION_PLAN_LIST = "v2/microAction/plan/list";
    public static final String MICRO_ACTION_PLAN_LIST_N = "203001";
    /**
     * 微行动目录查询接口
     */
    public static final String MICRO_ACTION_LIST = "v2/microAction/list";
    public static final String MICRO_ACTION_LIST_N = "203002";
    /**
     * 微行动详情查询接口
     */
    public static final String MICRO_ACTION_ADD = "v2/microAction/add";
    public static final String MICRO_ACTION_ADD_N = "203003";
    /**
     * 添加微行动
     */
    public static final String MICRO_ACTION_SAVE = "v2/microAction/save";
    public static final String MICRO_ACTION_SAVE_N = "203004";
    /**
     * 微行动签到接口
     */
    public static final String MICRO_ACTION_SIGN_IN = "v2/microAction/signIn";
    public static final String MICRO_ACTION_SIGN_IN_N = "203005";
    /**
     * 用户心情记录
     */
    public static final String MICRO_ACTION_MOD_SAVE = "v2/microAction/mod/save";
    public static final String MICRO_ACTION_MOD_SAVE_N = "203006";
    /**
     * 微行动放弃接口
     */
    public static final String MICRO_ACTION_GIVE_UP = "v2/microAction/giveUp";
    public static final String MICRO_ACTION_GIVE_UP_N = "203007";
    /**
     * 查询所有参与过的微行动列表
     */
    public static final String MICRO_ACTION_HISTORY_LIST = "v2/microAction/history/list";
    public static final String MICRO_ACTION_HISTORY_LIST_N = "203008";
    /**
     * 微行动历史接口
     */
    public static final String MICRO_ACTION_HISTORY = "v2/microAction/plan/detail";
    public static final String MICRO_ACTION_HISTORY_N = "203009";
    /**
     * 分页查询
     */
    public static final String MICRO_ACTION_HISTORY_PAGE_DETAIL = "v2/microAction/plan/page/detail";
    public static final String MICRO_ACTION_HISTORY_PAGE_DETAIL_N = "203010";
    /**
     * 微行动目录详情查询接口
     */
    public static final String MICRO_ACTION_FOLDER_DETAIL = "v2/microAction/folder/detail";
    public static final String MICRO_ACTION_FOLDER_DETAIL_N = "203002";
    /**
     * 微行动详情查询
     */
    public static final String MICRO_ACTION_DETAIL = "v2/microAction/detail";
    public static final String MICRO_ACTION_DETAIL_N = "203006";

    /**
     * banner详情查询接口
     */
    public static final String MICOR_ACTION_BANNER_DETAIL = "v2/microAction/banner/detail";
    public static final String MICOR_ACTION_BANNER_DETAIL_N = "203010";

    /**
     * 用户信息查询
     */
    public static final String USER_INFO = "v2/user/info";
    public static final String USER_INFO_N = "202002";
    /**
     * SVD问卷保存结果
     */
    public static final String QUESTION_CVD_SAVE = "question/cvd/save";
    public static final String QUESTION_CVD_SAVE_N = "140003";
    /**
     * 问卷查询结果
     */
    public static final String QUESTION_DETAIL = "question/detail";
    public static final String QUESTION_DETAIL_N = "140002";
    /**
     * 处方模板查询
     */
    public static final String RX_TEMPLATE_LIST = "v2/scheme/rxTemplate/list";
    public static final String RX_TEMPLATE_LIST_N = "204003";
    /**
     * 处方模板详情
     */
    public static final String RX_TEMPLATE_DETAIL = "v2/scheme/rxTemplate/detail";
    public static final String RX_TEMPLATE_DETAIL_N = "204004";
    /**
     * 运动目标查询接口
     */
    public static final String TARGET_LIST = "target/list";
    public static final String TARGET_LIST_N = "110007";
    /**
     * 训练首页的内容
     */
    public static final String TRAIN_MAIN_LIST = "train/main/list";
    public static final String TRAIN_MAIN_LIST_N = "160001";
    /**
     * 推荐处方概览
     */
    public static final String PLAN_RECOMMEND_VIEW = "v2/sport/plan/recommend/view";
    public static final String PLAN_RECOMMEND_VIEW_N = "208002";
    /**
     * 确认推荐处方生成
     */
    public static final String PLAN_SURE = "v2/sport/plan/save";
    public static final String PLAN_SURE_N = "208003";
    /**
     * 计划详情查询接口
     */
    public static final String PLAN_DETAIL = "v2/sport/plan/detail";
    public static final String PLAN_DETAIL_N = "208001";
    /**
     * 查询运动计划日程的详情信息
     */
    public static final String PLAN_SCHEDULE_DETAIL = "v2/sport/plan/schedule/detail";
    public static final String PLAN_SCHEDULE_DETAIL_N = "208004";
    /**
     *
     * 运动计划退出
     */
    public static final String PLAN_QUIT = "v2/sport/plan/quit";
    public static final String PLAN_QUIT_N = "208005";
    /**
     * 训练结果详情查询
     */
    public static final String TRAIN_RESULT_DETAIL = "train/result/detail";
    public static final String TRAIN_RESULT_DETAIL_N = "160003";
    /**
     * 运动目标保存接口
     */
    public static final String USER_TARGET_SAVE = "user/target/save";
    public static final String USER_TARGET_SAVE_N = "120006";
    /**
     * cvd问卷查询接口
     */
    public static final String QUESTION_CVD_RESULT = "question/cvd/result";
    public static final String QUESTION_CVD_RESULT_N = "140004";
    /**
     * 模板分类查询
     */
    public static final String RXTEMPLATE_CLASS_TYPE = "rxTemplate/classType";
    public static final String RXTEMPLATE_CLASS_TYPE_N = "130003";
    /**
     * 自选方案添加
     */
    public static final String TRAIN_SCHEME_ADD = "v2/scheme/add";
    public static final String TRAIN_SCHEME_ADD_N = "204005";
    /**
     * 自选方案退出
     */
    public static final String TRAIN_QUIT = "v2/scheme/quit";
    public static final String TRAIN_QUIT_N = "204005";

    /**
     * 训练结果上报
     */
    public static final String TRAIN_RESULT_REPORT = "v2/train/result/report";
    public static final String TRAIN_RESULT_REPORT_N = "209001";
    /**
     * 自定义运动计划概览
     */
    public static final String PLAN_ADD_VIEW = "plan/add/view";
    public static final String PLAN_ADD_VIEW_N = "150005";
    /**
     * 推荐进阶查询
     */
    public static final String PLAN_RECOMMEND_QUERY = "plan/recommend/query";
    public static final String PLAN_RECOMMEND_QUERY_N = "150007";
    /**
     * 训练历史接口查询
     */
    public static final String TRAIN_HISTORY_LIST = "train/history/list";
    public static final String TRAIN_HISTORY_LIST_N = "160005";
    /**
     * 训练历史查询
     */
    public static final String TRAIN_HISTORY_DETAIL = "train/history/detail";
    public static final String TRAIN_HISTORY_DETAIL_N = "160006";
    /**
     * 运动计划训练历史接口查询
     */
    public static final String PLAN_RECORD_LIST = "v2/sport/plan/schedule/history";
    public static final String PLAN_RECORD_LIST_N = "208006";
    /**
     * 运动计划训练历史详情查询接口
     */
    public static final String PLAN_RECORD_Detail = "train/history/plan/detail";
    public static final String PLAN_RECORD_DetailN = "160009";
    /**
     * 跑步训练结果
     */
    public static final String RUN_TRAIN_REPORT = "v2/run/train/report";
    /**
     * 跑步训练结果列表
     */
    public static final String RUN_TRAIN_LIST = "v2/run/train/list";
    public static final String RUN_TRAIN_REPORT_N = "205003";
    public static final String RUN_TRAIN_LIST_N = "205004";
    /**
     * 跑步训练详情
     */
    public static final String RUN_TRAIN_DETAIL = "v2/run/train/detail";
    public static final String RUN_TRAIN_DETAIL_N = "205005";
    /**
     * 跑步统计结果查询
     */
    public static final String RUN_TRAIN_STATISTICS = "run/train/statistics";
    public static final String RUN_TRAIN_STATISTICS_N = "190004";
    /**
     * 删除跑步记录
     */
    public static final String RUN_TRAIN_DELETE = "v2/run/train/delete";
    public static final String RUN_TRAIN_DELETE_N = "205007";
    /**
     * 功率车训练结果列表查询
     */
    public static final String CYCLE_TRAIN_LIST = "v2/cycle/train/list";
    public static final String CYCLE_TRAIN_LIST_N = "215001";
    /**
     * 功率车训练结果详情查询
     */
    public static final String CYCLE_TRAIN_DETAIL = "v2/cycle/train/detail";
    public static final String CYCLE_TRAIN_DETAIL_N = "215002";
    /**
     * 活动数据查询
     */
    public static final String HEALTH_ACTION_STEP = "v2/health/action/step";
    public static final String HEALTH_ACTION_STEP_N = "210003";
    /**
     * 活力值数据查询
     */
    public static final String HEALTH_ACTION_VITALITY = "v2/health/action/vitality";
    public static final String HEALTH_ACTION_VITALITY_N = "210002";
    /**
     * 今日行动列表
     */
    public static final String HEALTH_ACTION_LIST = "v2/health/action/list";
    public static final String HEALTH_ACTION_LIST_N = "210001";
    /**
     * 健康tips查询
     */
    public static final String HEALTH_TIPS = "health/tips";
    public static final String HEALTH_TIPS_N = "110009";
    /**
     * 自选方案查询接口
     */
    public static final String SCHEME_LIST = "v2/scheme/list";
    public static final String SCHEME_LIST_N = "204001";
    /**
     * 所有方案列表的查询接口
     */
    public static final String SCHEME_RX_TEMPLATE_CLASSTYPE = "v2/scheme/rxTemplate/classType";
    public static final String SCHEME_RX_TEMPLATE_CLASSTYPE_N = "204002";
    /**
     * 保存客户反馈
     */
    public static final String SCHEME_RPE = "v2/train/rpe";
    public static final String SCHEME_RPE_N = "209002";
    /**
     * 保存客户反馈
     */
    public static final String RUN_TRAIN_RPE = "v2/run/train/rpe";
    public static final String RUN_TRAIN_RPE_N = "205006";
    /**
     * 用户反馈上报接口
     */
    public static final String SCHEME_FEEDBACK = "v2/train/feedback";
    public static final String SCHEME_FEEDBACK_N = "209003";
    /**
     * 训练结果详情接口
     */
    public static final String SCHEME_HISTORY_DETAIL = "v2/train/detail";
    public static final String SCHEME_HISTORY_DETAIL_N = "209004";
    /**
     * 训练历史结果列表
     */
    public static final String SCHEME_HISTORY_LIST = "v2/scheme/history/list";
    public static final String SCHEME_HISTORY_LIST_N = "204010";
    /**
     * 有氧运动信息
     */
    public static final String AEROBIC_INFO = "v2/aerobic/info";
    public static final String AEROBIC_INFO_N = "205001";
    /**
     * 有氧运动配置修改
     */
    public static final String AEROBIC_CONFIG_UPDATE = "v2/aerobic/config/update";
    public static final String AEROBIC_CONFIG_UPDATE_N = "205002";
    /**
     * 饮食查询列表
     */
    public static final String DIET_LIST = "v2/diet/list";
    public static final String DIET_LIST_N = "206001";
    /**
     * 饮食新增
     */
    public static final String DIET_DATA_SAVE = "v2/diet/save";
    public static final String DIET_DATA_SAVE_N = "206002";
    /**
     * 饮食删除
     */
    public static final String DIET_DELETE = "v2/diet/delete";
    public static final String DIET_DELETE_N = "206003";
    /**
     * 体重检测数据查询接口
     */
    public static final String MONITOR_WEIGHT_LIST = "v2/monitor/weight/list";
    public static final String MONITOR_WEIGHT_LIST_N = "207001";
    /**
     * 体重新增接口
     */
    public static final String MONITOR_WEIGHT_SAVE = "v2/monitor/weight/save";
    public static final String MONITOR_WEIGHT_SAVE_N = "207002";
    /**
     * 体重检测数据分页查询接口
     */
    public static final String MONITOR_WEIGHT_CHART = "v2/monitor/weight/chart";
    public static final String MONITOR_WEIGHT_CHART_N = "207003";
    /**
     * 血压检测数据查询接口
     */
    public static final String MONITOR_BLOODPRESSURE_LIST = "v2/monitor/bloodPressure/list";
    public static final String MONITOR_BLOODPRESSURE_LIST_N = "207004";
    /**
     * 血压新增接口
     */
    public static final String MONITOR_BLOODPRESSURE_SAVE = "v2/monitor/bloodPressure/save";
    public static final String MONITOR_BLOODPRESSURE_SAVE_N = "207006";
    /**
     * 血压检测数据分页查询接口
     */
    public static final String MONITOR_BLOODPRESSURE_CHART = "v2/monitor/bloodPressure/chart";
    public static final String MONITOR_BLOODPRESSURE_CHART_N = "207005";
    /**
     * 血糖检测数据查询接口
     */
    public static final String MONITOR_BLOODSUGAR_LIST = "v2/monitor/bloodSugar/list";
    public static final String MONITOR_BLOODSUGAR_LIST_N = "207007";
    /**
     * 血糖新增接口
     */
    public static final String MONITOR_BLOODSUGAR_SAVE = "v2/monitor/bloodSugar/save";
    public static final String MONITOR_BLOODSUGAR_SAVE_N = "207009";
    /**
     * 血糖检测数据分页查询接口
     */
    public static final String MONITOR_BLOODSUGAR_CHART = "v2/monitor/bloodSugar/chart";
    public static final String MONITOR_BLOODSUGAR_CHART_N = "207008";
    /**
     * 体检报告列表
     */
    public static final String ARCHIVES_CRUX_LIST = "v2/archives/crux/list";
    public static final String ARCHIVES_CRUX_LIST_N = "211004";
    /**
     * 关键指标查询一次查询
     */
    public static final String ARCHIVES_CRUX_DETAIL = "v2/archives/crux/detail";
    public static final String ARCHIVES_CRUX_DETAIL_N = "211005";
    /**
     * 关键指标列表查询
     */
    public static final String ARCHIVES_CRUX_COMPARE = "v2/archives/crux/compare";
    public static final String ARCHIVES_CRUX_COMPARE_N = "211006";
    /**
     * 体适能列表
     */
    public static final String FITNESS_LIST = "v2/fitness/list";
    public static final String FITNESS_LIST_N = "212001";
    /**
     * 机械体适能
     */
    public static final String FITNESS_ACMEWAY = "v2/fitness/acmeway";
    public static final String FITNESS_ACMEWAY_N = "212002";
    /**
     * 体感体适能
     */
    public static final String FITNESS_YOUNGLIGHTING_ADULT = "v2/fitness/younglighting/adult";
    public static final String FITNESS_YOUNGLIGHTING_ADULT_N = "212003";
    /**
     * 体姿体态
     */
    public static final String FITNESS_YOUNGLIGHTING_POSTURE = "v2/fitness/younglighting/posture";
    public static final String FITNESS_YOUNGLIGHTING_POSTURE_N = "212006";
    /**
     * 人体成分报告
     */
    public static final String FITNESS_YOUNGLIGHTING_COMPOSITION = "v2/fitness/younglighting/composition";
    public static final String FITNESS_YOUNGLIGHTING_COMPOSITION_N = "212007";
    /**
     * 健康档案信息查询
     */
    public static final String ARCHIVES_LIST = "v2/archives/list";
    public static final String ARCHIVES_LIST_N = "211001";
    /**
     * 检测心率数据查询
     */
    public static final String MONITOR_HR_REST_LIST = "v2/monitor/hrRest/list";
    public static final String MONITOR_HR_REST_LIST_N = "207010";
    /**
     * 检测心率数据分页查询
     */
    public static final String MONITOR_HR_REST_CHART = "v2/monitor/hrRest/chart";
    public static final String MONITOR_HR_REST_CHART_N = "207012";
    /**
     * 安静心率数据上报
     */
    public static final String MONITOR_HR_REST_SAVE = "v2/monitor/hrRest/save";
    public static final String MONITOR_HR_REST_SAVE_N = "207011";
    /**
     * 干预方案列表
     */
    public static final String ARCHIVES_INTERVENE_SCHEME_LIST = "v2/archives/interveneScheme/list";
    public static final String ARCHIVES_INTERVENE_SCHEME_LIST_N = "211002";
    /**
     * 干预方案详情
     */
    public static final String ARCHIVES_INTERVENE_SCHEME_DETAL = "v2/archives/interveneScheme/detail";
    public static final String ARCHIVES_INTERVENE_SCHEME_DETAIL_N = "211003";
    /**
     * 用药记录
     */
    public static final String ARCHIVES_DRUG_SITUATION_LIST = "v2/archives/drugSituation/list";
    public static final String ARCHIVES_DRUG_SITUATION_LIST_N = "211002";
    /**
     * 活动列表
     */
    public static final String ACTIVITY_LIST = "v2/activity/list";
    public static final String ACTIVITY_LIST_N = "214001";
    /**
     * 意见反馈图片上传
     */
    public static final String USER_FEEDBACK = "v2/user/feedback";
    public static final String USER_FEEDBACK_N = "202006";

    /**
     * 重置修改密码
     */
    public static final String MODIFY_NewPASSWORD = "v2/user/modifyPassword";
    public static final String MODIFY_NewPASSWORD_N = "202007";

    /**
     * 设备绑定接口
     */
    public static final String USER_DEVICE_BIND = "v2/user/device/bind";
    public static final String USER_DEVICE_BIND_N = "213001";
    /**
     * 设备设备解绑
     */
    public static final String USER_DEVICE_UNBIND = "v2/user/device/unbind";
    public static final String USER_DEVICE_UNBIND_N = "213002";
    /**
     * 用户设备信息查询
     */
    public static final String USER_DEVICE_INFO = "v2/user/device/info";
    public static final String USER_DEVICE_INFO_N = "213003";
    /**
     * 设备绑定情况查询
     */
    public static final String DEVICE_BIND_INFO = "v2/device/bind/info";
    public static final String DEVICE_BIND_INFO_N = "213004";
    /**
     * 配置手环功能
     */
    public static final String USER_BRACELET_CONFIG_SAVE = "v2/user/bracelet/config/save";
    public static final String USER_BRACELET_CONFIG_SAVE_N = "213005";
    /**
     * 查询手环的配置信息
     */
    public static final String USER_BRACELET_CONFIG_INFO = "v2/user/bracelet/config/info";
    public static final String USER_BRACELET_CONFIG_INFO_N = "213006";
    /**
     * 手环活动数据的上报接口
     */
    public static final String USER_BRACELET_DATA_REPORT = "v2/user/bracelet/data/report";
    public static final String USER_BRACELET_DATA_REPORT_N = "213007";
    /**
     * 手环睡眠数据查询
     */
    public static final String USER_BRACELET_SLEEP_LIST = "v2/user/bracelet/sleep/list";
    public static final String USER_BRACELET_SLEEP_LIST_N = "213010";
    /**
     * 手环活动数据查询
     */
    public static final String USER_BRACELET_ACTIVITY_LIST = "v2/user/bracelet/activity/list";
    public static final String USER_BRACELET_ACTIVITY_LIST_N = "213008";
    /**
     * 活动详情
     */
    public static final String ACTIVITY_DETAIL = "v2/activity/detail";
    public static final String ACTIVITY_DETAIL_N = "250002";
    /**
     * 退出活动接口
     */
    public static final String ACTIVITY_QUIT = "v2/activity/quit";
    public static final String ACTIVITY_QUIT_N = "250006";
    /**
     * 活动参与人员查询
     */
    public static final String ACTIVITY_USER_LIST = "v2/activity/user/list";
    public static final String ACTIVITY_USER_LIST_N = "250003";
    /**
     * 活动参与
     */
    public static final String ACTIVITY_JOIN = "v2/activity/join";
    public static final String ACTIVITY_JOIN_N = "250005";
    /**
     * 查询团队列表接口
     */
    public static final String ACTIVITY_TEAM_LIST = "v2/activity/team/list";
    public static final String ACTIVITY_TEAM_LIST_N = "250006";
    /**
     * 查询团队成员接口
     */
    public static final String ACTIVITY_TEAM_USER_LIST = "v2/activity/team/user/list";
    public static final String ACTIVITY_TEAM_USER_LIST_N = "250007";
    /**
     * 团队详情查询接口
     */
    public static final String ACTIVITY_TEAM_DETAIL = "v2/activity/team/detail";
    public static final String ACTIVITY_TEAM_DETAIL_N = "250008";
    /**
     * 查询每日参与率
     */
    public static final String ACTIVITY_TEAM_PARTICIPATION_LIST = "v2/activity/team/participation/list";
    public static final String ACTIVITY_TEAM_PARTICIPATION_LIST_N = "250009";
    /**
     * 团队信息保存
     */
    public static final String ACTIVITY_TEAM_SAVE = "v2/activity/team/save";
    public static final String ACTIVITY_TEAM_SAVE_N = "250010";
    /**
     * 团队信息查询
     */
    public static final String ACTIVITY_TEAM_INFO = "v2/activity/team/info";
    public static final String ACTIVITY_TEAM_INFO_N = "250011";
    /**
     * 地图活动闯关接口
     */
    public static final String ACTIVITY_MAP_BREAK = "v2/activity/map/break";
    public static final String ACTIVITY_MAP_BREAK_N = "250012";
    /**
     * 步数上报接口
     */
    public static final String USER_STEPS_REPORT = "v2/user/steps/report";
    public static final String USER_STEPS_REPORT_N = "v2/user/steps/report";
    /**
     * 广告图查询接口
     */
    public static final String AD_PICTURE_LIST = "v2/adPicture/list";
    public static final String AD_PICTURE_LIST_N = "201008";
    /**
     * 阶段小结
     */
    public static final String ARCHIVES_STAGE_SUMMARY_LIST = "v2/archives/stageSummary/list";
    public static final String ARCHIVES_STAGE_SUMMARY_LIST_N = "211009";
    /**
     * 阶段小结详情数据
     */
    public static final String ARCHIVES_STAGE_SUMMARY_DETAIL = "v2/archives/stageSummary/detail";
    public static final String ARCHIVES_STAGE_SUMMARY_DETAIL_N = "211010";

    /**
     * 危险因素数据
     */
    public static final String ARCHIVES_RISK_FACTOR_LIST = "v2/archives/riskFactor/list";
    public static final String ARCHIVES_RISK_FACTOR_LIST_N = "211008";
    /**
     * 生命之树查询
     */
    public static final String LIFE_TREE_INFO = "v2/life/tree/info";
    public static final String LIFE_TREE_INFO_N = "216001";
    /**
     * 生命之树种植接口
     */
    public static final String LIFE_TREE_SOW = "v2/life/tree/sow";
    public static final String LIFE_TREE_SOW_N = "216002";
    /**
     * 生命之树收取接口
     */
    public static final String LIFE_TREE_ENERGY_COLLECT = "v2/life/tree/energy/collect";
    public static final String LIFE_TREE_ENERGY_COLLECT_N = "216003";
    /**
     * 生命之树一键收取接口
     */
    public static final String LIFE_TREE_ENERGY_COLLECT_ALL = "v2/life/tree/energy/collect/all";
    public static final String LIFE_TREE_ENERGY_COLLECT_ALL_N = "216004";
    /**
     * 获取未收取能量列表
     */
    public static final String LIFE_TREE_ENERGY_LIST = "v2/life/tree/energy/list";
    public static final String LIFE_TREE_ENERGY_LIST_N = "216005";
    /**
     * 领取收获到的种子接口
     */
    public static final String LIFE_TREE_SEED_COLLECT = "v2/life/tree/seed/collect";
    public static final String LIFE_TREE_SEED_COLLECT_N = "216006";
    /**
     * 任务查询接口
     */
    public static final String LIFE_TREE_TASK_LIST = "v2/life/tree/task/list";
    public static final String LIFE_TREE_TASK_LIST_N = "216007";
    /**
     * 运动详情查询接口
     */
    public static final String LIFE_TREE_TASK_SPORT = "v2/life/tree/task/sport";
    public static final String LIFE_TREE_TASK_SPORT_N = "216008";
    /**
     * 指标监测查询接口
     */
    public static final String LIFE_TREE_TASK_MONITOR = "v2/life/tree/task/monitor";
    public static final String LIFE_TREE_TASK_MONITOR_N = "216009";
    /**
     * 分享信息查询接口
     */
    public static final String LIFE_TREE_SEED_SHARE = "v2/life/tree/seed/share";
    public static final String LIFE_TREE_SEED_SHARE_N = "216010";
    /**
     * 能量信息查询接口
     */
    public static final String LIFE_TREE_ENERGY_RECORD_LIST = "v2/life/tree/energy/record/list";
    public static final String LIFE_TREE_ENERGY_RECORD_LIST_N = "2160011";
    /**
     * 种子记录查询接口
     */
    public static final String LIFE_TREE_SEED_RECORD_LIST = "v2/life/tree/seed/record/list";
    public static final String LIFE_TREE_SEED_RECORD_LIST_N = "216012";
    /**
     * 关注人查询接口
     */
    public static final String LIFE_TREE_ATTENTION_LIST = "v2/life/tree/attention/list";
    public static final String LIFE_TREE_ATTENTION_LIST_N = "216013";
    /**
     * 影响的人查询接口
     */
    public static final String LIFE_TREE_EFFECT_LIST = "v2/life/tree/effect/list";
    public static final String LIFE_TREE_EFFECT_LIST_N = "216014";
    /**
     * 关注好友接口
     */
    public static final String LIFE_TREE_PAY_ATTENTION = "v2/life/tree/pay/attention";
    public static final String LIFE_TREE_PAY_ATTENTION_N = "216015";
    /**
     * 取消好友接口
     */
    public static final String LIFE_TREE_ATTENTION_CANCEL = "v2/life/tree/attention/cancel";
    public static final String LIFE_TREE_ATTENTION_CANCEL_N = "216016";

    /**
     * 查询客户的产品信息（产品内容、周期、需求等）
     */
    public static final String ARCHIVES_CARD_TYPE_INFO = "v2/archives/cardType/info";
    public static final String ARCHIVES_CARD_TYPE_INFO_N = "211011";
    /**
     * 查询客户历史的产品列表（产品内容、周期、需求等）
     */
    public static final String ARCHIVES_CARD_TYPE_LIST = "v2/archives/cardType/list";
    public static final String ARCHIVES_CARD_TYPE_LIST_N = "211012";
    /**
     * 查询阶段干预方案的详细信息
     */
    public static final String ARCHIVES_STAGE_INTERVENE_SCHEME_DETAIL = "v2/archives/stageInterveneScheme/detail";
    public static final String ARCHIVES_STAGE_INTERVENE_SCHEME_DETAIL_N = "211013";
    /**
     * 查询用户当天的步数来源
     */
    public static final String USER_STEPS_LIST = "v2/user/steps/list";
    public static final String USER_STEPS_LIST_N = "202008";
    /**
     * 查询运动计划历史数据
     */
    public static final String SPORT_PLAN_HISTORY_LIST = "v2/sport/plan/history/list";
    public static final String SPORT_PLAN_HISTORY_LIST_N = "208007";
    /**
     * 查询最新的未读消息列表
     */
    public static final String MESSAGE_NEWEST_LIST = "v2/message/newest/list";
    public static final String MESSAGE_NEWEST_LIST_N = "217001";
    /**
     * 查询某一个分类的消息列表
     */
    public static final String MESSAGE_LIST = "v2/message/list";
    public static final String MESSAGE_LIST_N = "217002";
    /**
     * 查询消息提醒配置信息
     */
    public static final String MESSAGE_CONFIG_INFO = "v2/message/config/info";
    public static final String MESSAGE_CONFIG_INFO_N = "217003";
    /**
     * 修改消息提醒配置信息
     */
    public static final String MESSAGE_CONFIG_UPDATE = "v2/message/config/update";
    public static final String MESSAGE_CONFIG_UPDATE_N = "217004";
    /**
     * 更新训练消息提醒配置信息
     */
    public static final String MESSAGE_TRAINREMIND_CONFIG_UPDATE = "v2/message/trainRemind/config/update";
    public static final String MESSAGE_TRAINREMIND_CONFIG_UPDATE_N = "217005";
    /**
     * 查询某一个分类的未读的消息列表
     */
    public static final String MESSAGE_UNREAD_LIST = "v2/message/unread/list";
    public static final String MESSAGE_UNREAD_LIST_N = "217006";
    /**
     * 更新消息为已读状态
     */
    public static final String MESSAGE_READ = "v2/message/read";
    public static final String MESSAGE_READ_N = "217007";

}
