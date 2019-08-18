auto.waitFor()
device.keepScreenOn()
device.vibrate(2000);
device.setAlarmVolume(0)
device.setMusicVolume(0) 
device.setAlarmVolume(0)


function run() {
    for (var i = 0; i < 3; i++) {
        toast("准备开始搬砖")
        sleep(2000)
    }
    /** */
    清后台()
    广多多()
    
    清后台()
    奇秀() 

    清后台()
    红包视频()
    
    清后台()
    刷宝()

    清后台()
    微鲤()

    清后台()
    聚看点()

    清后台()
    掌上热点()
       
    清后台()
    种子视频()
    
    清后台()
    盈贝头条()
    
    清后台()
    奇秀()   
    
    清后台()
    悦头条()    
     
    清后台()
    欢乐赚()
    
    清后台()
    中青看点()
    
    清后台()
    快狗视频()
     
    清后台()
    好乡音()
    
    exit()
    

}

var 取屏幕高度 = device.height
var 取屏幕宽度 = device.width
log("宽-->"+取屏幕宽度+",高-->"+取屏幕高度)
var 找到 = false
var 调试 = false
var 进程

function find_zx(包名) {
    找到 = false
    if (packageName(包名).exists()) {
        找到 = true
    } else {
        找到 = false
    }
}
function 应用是否在前台(包名, 应用名称) {
    find_zx(包名, 应用名称)
    if (找到 == false) {
        var flag=launch(包名)
        toast("OPEN" + 应用名称)

        sleep(10000)
    }
}
function 应用是否在前台短(包名, 应用名称) {
    find_zx(包名, 应用名称)
    if (找到 == false) {
        var flag=launch(包名)

        sleep(1000)
    }
}
function 清后台() {

    if (device.release >= "6.9") {
        home()
        sleep(2000);
        recents()
        sleep(6000);

        查找控件第一个直接点击(text, "全部清除")
        查找控件第一个直接点击(idContains, "clearAnimView")
        查找控件第一个直接点击(idContains, "circle_button")
        查找控件全部点击(text, "全部清除")
        查找控件全部点击(text, "全部清理")
        查找控件全部点击(idContains, "clearAnimView")
        查找控件全部点击(idContains, "circle_button")
        查找控件全部点击(idContains, "recent_igmbutton_clear_all")
        查找控件第一个直接点击(id, "clear_all_recents_image_button")
    } else {
        Home()
        sleep(2000);
        recents()
        sleep(6000);
        查找控件第一个直接点击(text, "全部清除")
        查找控件第一个直接点击(id, "clearAnimView")
        查找控件第一个直接点击(id, "circle_button")
        查找控件第一个直接点击(text, "全部清除")
        查找控件第一个直接点击(text, "全部清理")
        查找控件第一个直接点击(id, "clearAnimView")
        查找控件第一个直接点击(id, "circle_button")
        查找控件第一个直接点击(id, "recent_igmbutton_clear_all")
        查找控件第一个直接点击(id, "leui_recent_clear_all_txtview")
        查找控件第一个直接点击(idContains, "clear_all_recents_image_button")
        查找控件第一个直接点击(id, "clear_all_recents_image_button")
        直点坐标(363, 1260)

    }


    if (device.release >= "6.9") {
        home()
        sleep(2000);
    } else {
        Home()
        sleep(2000);
    }

}
var 找到控件 = false
function 点击新闻选择控件(控件类型, 控件条件) {
    找到控件 = false
    if (控件类型(控件条件).exists()) {
        var c = 控件类型(控件条件).find()
        var 成员数 = (c.length)
        if (成员数 > 0) {
            for (var i = 0; i < 成员数 - 1; i++) {
                var b = 控件类型(控件条件).find().get(i).bounds()
                x = b.centerX()
                y = b.centerY()
                if (x > 1 && x < 取屏幕宽度 && y < 取屏幕高度 * 0.9 && y > 取屏幕高度 * 0.3) {
                    if (调试 == true) {
                        toast("第" + 成员数 + "个参数符合条件")
                    }
                    找到控件 = true
                    点击(x, y)
                    break
                }
            }

        }
    }

}
function 查找控件第一个(控件类型, 控件条件) {
    找到 = false
    var c = 控件类型(控件条件).find()
    var 成员数 = (c.length)
    if (成员数 > 0) {
        for (var i = 0; i < 成员数; i++) {
            var b = 控件类型(控件条件).find().get(i).bounds()
            x = b.centerX()
            y = b.centerY()
            if (x > 0 && x < 取屏幕宽度 && y < 取屏幕高度 && y > 0) {
                找到 = true
                if (调试 == true) {
                    toast("找到了" + x + "," + y + 控件条件)
                }
                break
            }
            sleep(2000)
        }
    }
}
function 查找控件第一个直接点击(控件类型, 控件条件) {
    找到 = false
    if (控件类型(控件条件).exists()) {

        var c = 控件类型(控件条件).find()
        var 成员数 = (c.length)
        if (成员数 > 0) {
            for (var i = 0; i < 成员数; i++) {
                var b = 控件类型(控件条件).find().get(i).bounds()
                x = b.centerX()
                y = b.centerY()
                if (x > 1 && x < 取屏幕宽度 && y < 取屏幕高度 && y > 1) {

                    
                    log("找到了" + x + "," + y + 控件条件)
                    
                    找到 = true
                    点击(x, y)
                    break
                }
            }

        }
    }

}

function 查找控件最后一个(控件类型, 控件条件) {
    找到 = false
    var c = 控件类型(控件条件).find()
    var 成员数 = (c.length)
    if (成员数 > 0) {
        var b = 控件类型(控件条件).find().get(成员数 - 1).bounds()
        x = b.centerX()
        y = b.centerY()
        sleep(2000)
        if (x > 0 && x < 取屏幕宽度 && y < 取屏幕高度 && y > 0) {
            找到 = true
            if (调试 == true) {
                toast("找到了" + x + "," + y + 控件条件)
            }
        }
    }
}
function 查找控件最后一个直接点击(控件类型, 控件条件) {
    找到 = false
    var c = 控件类型(控件条件).find()
    var 成员数 = (c.length)
    if (成员数 > 0) {
        var b = 控件类型(控件条件).find().get(成员数 - 1).bounds()
        x = b.centerX()
        y = b.centerY()
        sleep(2000)
        if (x > 0 && x < 取屏幕宽度 && y < 取屏幕高度 && y > 0) {
            找到 = true
            if (调试 == true) {
                toast("找到了" + x + "," + y + 控件条件)
            }
            点击(x, y)
        }
    }
}
function 查找控件全部点击(控件类型, 控件条件) {
    找到 = false
    var c = 控件类型(控件条件).find()
    var 成员数 = (c.length)
    if (成员数 > 0) {
        for (var i = 0; i < 成员数; i++) {
            var c = 控件类型(控件条件).find().get(i).bounds()
            x = c.centerX()
            y = c.centerY()
            if (x > 0 && x <= 取屏幕宽度 && y <= 取屏幕高度 && y > 0) {
                if (调试 == true) {
                    toast("找到了" + x + "," + y + 控件条件)
                }
                点击(x, y)
                sleep(2000)
            }
        }
    }
}

function 点击(x, y) {
    if (device.release >= "7") {
        ///press(x, y, 50);
        click(x, y)
        sleep(2000)
    } else {
        Tap(x, y)
        sleep(2000)
    }

}
function 返回() {
    if (device.release >= "7") {
        back()
        sleep(4000)
    } else {
        Back()
        sleep(4000)
    }

}

function 普通向上滑动() {
    var 随机数 = random(-100, 100)
    x1 = 取屏幕宽度 / 2 + 随机数
    y1 = 取屏幕高度 / 2 + 随机数
    var 随机数 = random(-100, 100)
    x2 = 取屏幕宽度 / 2 + 随机数
    y2 = 取屏幕高度 / 7 + 随机数
    if (device.release >= "7") {
        ///swipe(x1, y1, x2, y2, 4000)
        gesture(500, [取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.8 + 随机数], [取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.6 + 随机数])

    } else {
        Swipe(取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.8 + 随机数, 取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.6 + 随机数, 500)

    }
    sleep(2000)
}

function 普通向下滑动() {
    var 随机数 = random(-100, 100)
    x1 = 取屏幕宽度 / 2 + 随机数
    y1 = 取屏幕高度 / 2 + 随机数
    var 随机数 = random(-100, 100)
    x2 = 取屏幕宽度 / 2 + 随机数
    y2 = 取屏幕高度 / 7 + 随机数
    if (device.release >= "7") {
        ///swipe(x1, y1, x2, y2, 4000)
        gesture(500, [取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.6 + 随机数], [取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.8 + 随机数])

    } else {
        Swipe(取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.6 + 随机数, 取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.8 + 随机数, 500)

    }
    sleep(2000)
}


function 快速向上滑动() {
    var 随机数 = random(-100, 100)
    x1 = 取屏幕宽度 / 2 + 随机数
    y1 = 取屏幕高度 / 2 + 随机数
    var 随机数 = random(-100, 100)
    x2 = 取屏幕宽度 / 2 + 随机数
    y2 = 取屏幕高度 / 7 + 随机数
    if (device.release >= "7") {
        ///swipe(x1, y1, x2, y2, 4000)
        gesture(500, [取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.8 + 随机数], [取屏幕宽度 * 0.55 + 随机数, 取屏幕高度 * 0.4 + 随机数])

    } else {
        Swipe(取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.8 + 随机数, 取屏幕宽度 * 0.55 + 随机数, 取屏幕高度 * 0.4 + 随机数, 500)

    }
    sleep(2000)
}

function 快速向下滑动() {
    var 随机数 = random(-100, 100)
    x1 = 取屏幕宽度 / 2 + 随机数
    y1 = 取屏幕高度 / 2 + 随机数
    var 随机数 = random(-100, 100)
    x2 = 取屏幕宽度 / 2 + 随机数
    y2 = 取屏幕高度 / 7 + 随机数
    if (device.release >= "7") {
        ///swipe(x1, y1, x2, y2, 4000)
        gesture(500, [取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.4 + 随机数], [取屏幕宽度 * 0.55 + 随机数, 取屏幕高度 * 0.8 + 随机数])

    } else {
        Swipe(取屏幕宽度 * 0.5 + 随机数, 取屏幕高度 * 0.4 + 随机数, 取屏幕宽度 * 0.55 + 随机数, 取屏幕高度 * 0.8 + 随机数, 500)

    }
    sleep(2000)
}
function 直点坐标(x1, y1) {
    var 宽度变量 = x1 / 720
    var 高度变量 = y1 / 1280
    x = 取屏幕宽度 * 宽度变量
    y = 取屏幕高度 * 高度变量
    点击(x, y)
}

function 下滑刷新() {
    var 随机数 = random(100, 100)
    x1 = 取屏幕宽度 / 2 + 随机数
    y1 = 取屏幕高度 / 1.1
    var 随机数 = random(-100, 100)
    x2 = 取屏幕宽度 / 2 + 随机数
    y2 = 取屏幕高度 / 5
    if (device.release >= "7") {
        swipe(x2, y2, x1, y1, 1000)
    } else {
        Swipe(x2, y2, x1, y1, 1000)
    }
    sleep(2000)
}

function 上滑刷新() {
    var 随机数 = random(100, 100)
    x1 = 取屏幕宽度 / 2 + 随机数
    y1 = 取屏幕高度 / 1.5
    var 随机数 = random(-100, 100)
    x2 = 取屏幕宽度 / 2 + 随机数
    y2 = 取屏幕高度 / 5
    if (device.release >= "7") {
        swipe(x1, y1, x2, y2, 1000)
    } else {
        Swipe(x1, y1, x2, y2, 1000)
    }
    sleep(2000)
}

function 视频类滑动() {
    var 随机数 = random(-10, 10)
    x1 = 取屏幕宽度 / 2 + 随机数
    var 随机数 = random(-50, 50)
    y1 = 取屏幕高度 * 0.8 + 随机数


    var 随机数 = random(-10, 10)
    x2 = 取屏幕宽度 / 2 + 随机数
    var 随机数 = random(-10, 10)
    y2 = 取屏幕高度 * 0.4 + 随机数
    if (device.release >= "7") {
        swipe(x1, y1, x2, y2, 1)
    } else {
        Swipe(x1, y1, x2, y2, 100)
    }
    log("y1-"+y1+"y2-"+y2)
}

function findBtnExit(idStr){
    var count=0
    while(true){
        var flagBtn=id(idStr).findOne(2000)
        if(flagBtn!=null){
            break
        }
        sleep(1000)
        count++
        if(count>500){
            return
        }
    }
}

function 广多多() {
    log("打开广多多")
    包名 = "com.xs.healthtree"
    应用名称 = "广多多"
    应用是否在前台(包名, 应用名称)
    toast("开始广多多") 

    sleep(5000)
    查找控件第一个直接点击(text, "点击观看后领取")
    sleep(2000)
    查找控件第一个直接点击(id, "ivClose")

    while(true){
        sleep(2000)
        var avBtn=id("ivVideo").clickable(true).findOne(2000)

        if(avBtn!=null && avBtn.visibleToUser()){
            查找控件第一个直接点击(id, "ivVideo")
            sleep(32000)
            查找控件第一个直接点击(id, "tt_video_ad_close")
            sleep(1000)
            查找控件第一个直接点击(id, "dia_ok")
            sleep(2000)
            查找控件第一个直接点击(id, "ivClose")
            sleep(1000)
        }else{
            break
        }
    }
    
    sleep(2000)

    log("点击看一看")
    查找控件第一个直接点击(id, "mtMoney")

    sleep(5000)

    while(true){
        sleep(2000)
        直点坐标(363,660)
        sleep(2000)


        var ivIsHave=id("ivIsHave").findOne(2000)

        if(ivIsHave==null){
            var answerListView=id("rvAnswer").findOne(2000)
            if(answerListView!=null){
                var answerItems=answerListView.children()

                var selectPositions=new Array()
                for(var i=0;i<answerItems.size();i++){
                    var answerItem=answerItems.get(i)
                    var tvAnswer=answerItem.findOne(id("tvAnswer"))
                    
                    if(tvAnswer==null){
                        continue
                    }
                    
                    var selectListView=id("rvIssue").findOne()
                    var selectItems=selectListView.children()
    
    
                    for(var j=0;j<selectItems.size();j++){
                            
                        var selectItem=selectItems.get(j)
                        var tvIssue=selectItem.findOne(id("tvIssue"))
                        log("判断-->"+tvIssue.text()+"---"+tvAnswer.text())
                    
                        if(tvAnswer.text()==tvIssue.text()){
                            tvIssue.click()
                            sleep(2000)
                            break
                        }               
                        
                    } 
                                   
                }
                
                
                if(text("现在邀请").findOne(1000)!=null){
                    查找控件第一个直接点击(id, "dia_ok")
                    查找控件第一个直接点击(text,"取消分享")
                    break
                }
                
                sleep(1000)
                返回()
                sleep(1000)
                返回()
                sleep(1000)
                查找控件第一个直接点击(id, "ivRedClose")
                sleep(1000)
            }else{
                sleep(1000)
                返回()
                sleep(1000)
            }
           
        }else{
            sleep(1000)
            返回()
            sleep(1000)
        }

        for(var i=0;i<4;i++){
            普通向上滑动()
            sleep(1000)
        }
       
    }   
    
    toast("结束")
    
}

function 红包视频(){
    log("打开红包视频")
    包名 = "com.sanmiao.sound"
    应用名称 = "红包视频"   
    toast("开始红包视频") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    findBtnExit("share_count_layout")

    sleep(2000)
    for(var i=0;i<120;i++){
        应用是否在前台短(包名, 应用名称)

        查找控件第一个直接点击(id, "tt_video_ad_close")
        var index=0;
        while(true){
            var shareBtn=id("share_count_layout").findOne(1000)
            if(shareBtn!=null && shareBtn.visibleToUser()){
                sleep(1000)
                index++
            }else{
                sleep(2000)
               break
            }
            if(index>35){
                break
            }
        }
        
        log("观看下一个视频--->"+(i+1))
        视频类滑动()
    }
    查找控件第一个直接点击(id, "ly3")
    sleep(1000)
    查找控件第一个直接点击(id, "gold")
    sleep(1000)
    toast("结束")
}

function 刷宝(){
    log("打开刷宝")
    包名 = "com.jm.video"
    应用名称 = "刷宝短视频"
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    toast("开始刷宝") 
    for(var i=0;i<300;i++){     
        应用是否在前台短(包名, 应用名称)
        for(var j=0;j<15;j++){
            查找控件第一个直接点击(id, "idBtn")
            查找控件第一个直接点击(id, "imgClose")
            sleep(1000)
        }

        log("观看下一个视频--->"+(i+1))
        if(i%3==0){
            下滑刷新()
        }else{
            视频类滑动()
        }
        
    }
    sleep(2000)
    var taskBtn=id("tv_tab_title").className("TextView").text("任务").findOne(2000)
    if(taskBtn!=null){
        taskBtn.parent().click()
        
    }
    toast("结束")
}

function 微鲤(){
    log("打开微鲤")
    包名 = "cn.weli.story"
    应用名称 = "微鲤"
    toast("开始微鲤") 
    应用是否在前台(包名, 应用名称)

    sleep(5000)
    findBtnExit("et_search")   
    sleep(2000)

    for(var i=0;i<45;i++){
        log("看文章-->"+(i+1))
        
        直点坐标(320,400)
        for(var j=0;j<6;j++){
            log("循环-->"+j)
            for(var m=0;m<3;m++){
                快速向上滑动()
            }
            for(var m=0;m<3;m++){
                快速向下滑动()
            }              
        }
        返回()
        应用是否在前台短(包名, 应用名称)
        sleep(1000)
        下滑刷新()
    }
    sleep(2000)
    查找控件第一个直接点击(id, "rl_bottom_4")
    sleep(2000)
    查找控件第一个直接点击(id, "ll_not_sign")
    sleep(5000)
    查找控件第一个直接点击(text, "立即签到")
    toast("结束")
}

function 聚看点(){
    log("打开聚看点")
    包名 = "com.xiangzi.jukandian"
    应用名称 = "聚看点"
    toast("开始聚看点") 
    应用是否在前台(包名, 应用名称)

    sleep(10000)
    查找控件第一个直接点击(id, "v2_sign_sign_button")
    sleep(2000)
    查找控件第一个直接点击(id, "v2_sign_close_button")

    findBtnExit("iv_search")

    sleep(2000)
   
    for(var i=0;i<120;i++){
        log("看文章-->"+(i+1))
        查找控件第一个直接点击(id, "icon_home_left_timer_lq")
        sleep(1000)
        查找控件第一个直接点击(id, "dialog_close")
        sleep(1000)
        直点坐标(320,400)

        var flagBtn=text("评论得金币").findOne(2000)

        if(flagBtn!=null){
            for(var j=0;j<3;j++){
                log("循环-->"+j)
                for(var m=0;m<7;m++){
                    快速向上滑动()
                }
                for(var m=0;m<7;m++){
                    快速向下滑动()
                }              
            }
        }
                
        返回()
        sleep(1000)
        应用是否在前台短(包名, 应用名称)
        sleep(1000)      
        下滑刷新()
        sleep(2000)

    }
    sleep(2000)
    id("ll_tab2_layout").findOne().click()
    sleep(2000)
    for(var i=0;i<30;i++){
        直点坐标(360,330)
        sleep(2000)
        var flagBtn=text("评论得金币").findOne(2000)

        if(flagBtn!=null){
            sleep(30000)
        }
        
        返回()
        sleep(1000)
        应用是否在前台短(包名, 应用名称)
        sleep(1000)      
        下滑刷新()
        sleep(2000)
    }
    toast("结束")
}

function 盈贝头条(){
    log("打开盈贝头条")
    包名 = "com.aimotech.yingbeitt"
    应用名称 = "盈贝头条"
    toast("开始盈贝头条") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    findBtnExit("tv_search")
    
    sleep(2000)
    for(var i=0;i<60;i++){
        log("看文章-->"+(i+1))  
        
        直点坐标(333,333)
        
        sleep(2000)
        var flagBtn=id("iv_logo").findOne(2000)

        if(flagBtn!=null){
            for(var j=0;j<1;j++){
                log("循环-->"+j)
                for(var m=0;m<6;m++){
                    快速向上滑动()
                }
                for(var m=0;m<6;m++){
                    快速向下滑动()
                }              
            }
            返回()
        }else{
            返回()
            sleep(1000)
            普通向上滑动()
            sleep(1000)
            普通向上滑动()
            sleep(1000)
        }
        
       
        sleep(1000)
        应用是否在前台短(包名, 应用名称)    
        sleep(2000)
        普通向上滑动()
        sleep(1000)
        普通向上滑动()
        sleep(2000)
    }
    sleep(2000)
    id("tv_tab_text").className("TextView").text("视频").findOne().parent().parent().click()
    sleep(5000)
    for(var i=0;i<50;i++){
        log("看视频-->"+(i+1))
        
        直点坐标(333,333)

        sleep(2000)
        var flagBtn=id("fl_pro").findOne(2000)
        
        if(flagBtn!=null){
            sleep(36000)
            click(x,y)
        }
       
        返回()
        sleep(1000)
        应用是否在前台短(包名, 应用名称)    
        sleep(2000)
        普通向上滑动()
        sleep(1000)
        普通向上滑动()
        sleep(2000)
    }
    sleep(2000)
    id("tv_tab_text").className("TextView").text("任务大厅").findOne().parent().parent().click()
    sleep(2000)
    
    toast("结束")
}

function 奇秀(){
    log("打开奇秀")
    包名 = "com.iqiyi.qixiu"
    应用名称 = "奇秀"
    toast("开始奇秀") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)

    id("home_tab_select_text").className("TextView").text("视频").findOne().click()
    sleep(2000)
    直点坐标(533, 333)
    
    sleep(2000)
    for(var i=0;i<100;i++){
        log("看视频-->"+(i+1))
        应用是否在前台短(包名, 应用名称)
        sleep(1000)
        var goldFlag=id("short_video_task_circle_layout").findOne(1000)
        if(goldFlag!=null){
            sleep(15000)
            视频类滑动()
        } 
    }
    返回()
    sleep(2000)

    id("tabMessage").findOne().click()
    sleep(2000)
    /** 
    var taskListViewItems=id("task_recycleView").findOne().children()
    for(var i=0;i<taskListViewItems.size();i++){
        if(i==0){
            var item=taskListViewItems.get(i)
            var targetBtn=item.findOne(id("pb_button"))
            targetBtn.click()
        }
        if(i==3){

            break
        }
    }
   */
    toast("结束")
}

function 悦头条(){
    log("打开悦头条")
    包名 = "com.expflow.reading"
    应用名称 = "悦头条"
    toast("开始悦头条") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    findBtnExit("ll_search_news")
   
    sleep(2000)

    for(var i=0;i<80;i++){
        直点坐标(366, 833)
        
        sleep(2000)
        var flagBtn=id("rv_read_progress").findOne(2000)

        if(flagBtn!=null){
            for(var j=0;j<3;j++){
                for(var m=0;m<5;m++){
                   普通向上滑动()
                }
                for(var m=0;m<5;m++){
                    普通向下滑动()
                }     
            }
        }
        sleep(1000)
        返回()
        sleep(1000)
        应用是否在前台(包名, 应用名称)
        sleep(1000)
        普通向上滑动()
    }
    sleep(2000)
    查找控件第一个直接点击(id,"layout_earning")
    sleep(2000)
    for(var i=0;i<10;i++){
        查找控件最后一个直接点击(text,"立即邀请")
        while(true){
            var closeBtn=id("tt_video_ad_close").findOne(2000)
            if(closeBtn!=null){
                closeBtn.click()

                sleep(1000)
                break
            }
            sleep(1000)
        }
        sleep(2000)
    }
    sleep(2000)
    查找控件第一个直接点击(id,"layout_invite")
    sleep(2000)
    查找控件第一个直接点击(id,"llSignin")
    sleep(2000)
    查找控件第一个直接点击(id,"iv_close")

    toast("结束")
}

function 欢乐赚(){
    log("打开欢乐赚")
    包名 = "com.cmcm.happyearn"
    应用名称 = "欢乐赚"
    toast("开始欢乐赚") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    findBtnExit("homeImg")

    sleep(2000)

    var newsBtn=id("newsLayout").findOne(3000)
    if(newsBtn==null){
        return
    }
    newsBtn.click()

    for(var i=0;i<50;i++){
       
        log("浏览文章-->"+(i+1))
               
        sleep(1000)
        直点坐标(333, 512)
        sleep(2000)
        var redBtn=id("gameTaskRootView").findOne(2000)
        if(redBtn!=null){

            for(var j=0;j<3;j++){
                for(var m=0;m<3;m++){
                   普通向上滑动()
                }
                for(var m=0;m<3;m++){
                    普通向下滑动()
                }     
            }
        }
        sleep(1000)
        返回()
        sleep(1000)
        查找控件第一个直接点击(id,"btn_close")
        应用是否在前台短(包名, 应用名称)
        sleep(1000)
        普通向下滑动()
    }
    
    toast("结束")
}

function 掌上热点(){
    log("打开掌上热点")
    包名 = "com.kdlc.hnmcc"
    应用名称 = "掌上热点"
    toast("开始掌上热点") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    findBtnExit("ll_1")
    
    sleep(2000)
    for(var i=0;i<60;i++){

        log("浏览文章-->"+(i+1))
        直点坐标(387, 387)
        sleep(2000)
        var redBtn=id("iv_red_package").findOne(2000)
        if(redBtn!=null){
            for(var m=0;m<6;m++){
                普通向上滑动()
             }
             for(var m=0;m<6;m++){
                 普通向下滑动()
             }     
        }
        返回()
        sleep(1000)
        查找控件第一个直接点击(id,"ll_reward")
        查找控件第一个直接点击(id, "dialog_close")
        查找控件第一个直接点击(id, "iv_dialog_ad")
        应用是否在前台短(包名, 应用名称)
        sleep(2000)
        普通向上滑动()
        sleep(2000)
        普通向上滑动()
    }
    id("text_view").className("TextView").text("视频").findOne().parent().click()
    sleep(2000)
    id("tv_tab_title").className("TextView").text("有料").findOne().parent().click()
    sleep(2000)
    for(var i=0;i<50;i++){
        应用是否在前台(包名, 应用名称)
        log("浏览视频-->"+(i+1))
        直点坐标(366, 366)
        sleep(2000)
        var redBtn=id("iv_red_package").findOne(2000)
        if(redBtn!=null){
            sleep(30000)
        }
        返回()
        下滑刷新()
    }
    toast("结束")

}

function 辣手小视频(){
    log("打开辣手小视频")
    包名 = "com.qjy.lashou"
    应用名称 = "辣手小视频"
    toast("开始辣手小视频") 

    应用是否在前台(包名, 应用名称)

    sleep(2000)
    直点坐标(533, 333)
    sleep(2000)
    for(var i=0;i<110;i++){
        应用是否在前台短(包名, 应用名称)
        log("浏览视频-->"+(i+1))
        var goldFlag=id("gold_coin_progress").findOne(1000)
        if(goldFlag!=null){
            sleep(15000)
            视频类滑动()
        }  
    }
    返回()
    sleep(2000)
    查找控件第一个直接点击(id,"main_center_button")
    sleep(3000)
    查找控件第一个直接点击(text,"签到领金币")
    toast("结束")
}

function 好乡音(){
    log("打开好乡音")
    包名 = "com.lixg.zmdialect"
    应用名称 = "好乡音"
    toast("开始好乡音") 
    sleep(5000)
    for(var i=0;i<16;i++){
        log("看视频-->"+(i+1))
        应用是否在前台短(包名, 应用名称)
        sleep(2000)
        下滑刷新()
        sleep(2000)
        直点坐标(370, 200)
        sleep(2000)
        var loginBtn=text("登录微信").findOne(2000)
        if(loginBtn!=null){
            break
        }
        sleep(130000)
    }
    应用是否在前台短(包名, 应用名称)
    sleep(5000)
    查找控件第一个直接点击(text, "我的")
    查找控件第一个直接点击(text, "我的钱包")
    sleep(3000)
    查找控件第一个直接点击(text, "提现0.3元")
    toast("结束")
}

function 种子视频(){
    log("打开种子视频")
    包名 = "com.inke.gaia"
    应用名称 = "种子视频"
    toast("开始种子视频") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    findBtnExit("fh")
   
    sleep(2000)
    for(var i=0;i<50;i++){
        log("浏览视频-->"+(i+1))
              
        直点坐标(333, 444)

        var count=0;
        while(true){
            var btnFlag=id("a44").findOne(1000)
            
            if(btnFlag!=null){
                break
            }

            count++
            if(count>100){
                break
            }
            sleep(1000)
        }
        返回()
        sleep(1000)
        应用是否在前台短(包名, 应用名称)
        sleep(1000)
        下滑刷新()
        sleep(2000)
    }
    toast("结束")
}

function 红包头条(){
    log("打开红包头条")
    包名 = "com.martian.hbnews"
    应用名称 = "红包头条"
    toast("开始红包头条") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    findBtnExit("search_headline")
   
    sleep(2000)
    for(var i=0;i<100;i++){
        log("浏览文章-->"+(i+1))

        直点坐标(333, 1120)
        sleep(2000)
        var redBtn=id("circularProgressBar").findOne(3000)
        if(redBtn!=null){
            for(var m=0;m<4;m++){
                普通向上滑动()
             }
             for(var m=0;m<4;m++){
                 普通向下滑动()
             }     
        }
        返回()
        sleep(1000)
        应用是否在前台短(包名, 应用名称)
        sleep(1000)
        下滑刷新()
        sleep(2000)
    }
    sleep(2000)
    id("tab_textview").className("TextView").text("视频").findOne(3000).parent().click()
    sleep(2000)
    for(var i=0;i<50;i++){
        直点坐标(333, 300)
        log("浏览视频-->"+(i+1))
        
        sleep(2000)
        while(true){
            var redBtn=id("martian_video_guide").findOne(1000)
            if(redBtn==null){
                break
            }            
            sleep(1000)
        }
        返回()
        sleep(1000)
        应用是否在前台短(包名, 应用名称)
        sleep(1000)
        下滑刷新()
        sleep(2000)
    }
    toast("结束")
    
}

function 中青看点(){
    log("打开中青看点")
    包名 = "cn.youth.news"
    应用名称 = "中青看点"
    toast("开始中青看点") 
    应用是否在前台(包名, 应用名称)
    
    sleep(5000)
    查找控件第一个直接点击(id,"jp")
    sleep(2000)
    findBtnExit("wo")
    sleep(2000)

    for(var i=0;i<80;i++){
        log("看文章-->"+(i+1))  
       
        直点坐标(333,333)
        
        sleep(2000)
        var flagBtn=id("du").findOne(2000)

        if(flagBtn!=null){
            for(var j=0;j<1;j++){
                log("循环-->"+j)
                for(var m=0;m<6;m++){
                    快速向上滑动()
                }
                for(var m=0;m<6;m++){
                    快速向下滑动()
                }              
            }
        }
        
        返回()
        sleep(1000)
        应用是否在前台短(包名, 应用名称)    
        sleep(2000)
        下滑刷新()
        sleep(2000)
    }
    sleep(2000)
    查找控件第一个直接点击(id,"a4f")
    sleep(2000)
    for(var i=0;i<30;i++){
        log("看视频-->"+(i+1))
        
        直点坐标(333,333)

        sleep(2000)
        var flagBtn=id("du").findOne(2000)
        
        if(flagBtn!=null){
            sleep(36000)
            click(x,y)
        }
       
        返回()
        sleep(1000)
        应用是否在前台短(包名, 应用名称)    
        sleep(2000)
        下滑刷新()
    }
    sleep(2000)
    var mineBtn= className("RelativeLayout").clickable(true).depth(1).findOne(2000)
    if(mineBtn!=null){
        mineBtn.click()
    }
   
    sleep(2000)
    查找控件第一个直接点击(id,"im")
    sleep(2000)
    查找控件第一个直接点击(id,"a37")
    sleep(2000)
    for(var i=0;i<10;i++){
        var abtainBtn=text("看视频得青豆").findOne(2000)
        if(abtainBtn!=null){
            abtainBtn.click()
            sleep(35000)
        }
        
        var gotoBtn=text("点我继续领青豆").findOne(2000)
        if(gotoBtn!=null){
            gotoBtn.click()
            sleep(35000)
        }
        var closeBtn=className("ImageView").findOne(2000)
        if(closeBtn!=null){
            closeBtn.click()
        }
        sleep(2000)
        查找控件第一个直接点击(id,"jp")
    }
    sleep(2000)
    toast("结束")
}

function 快狗视频(){
    log("打开快狗视频")
    包名 = "com.xike.yipai"
    应用名称 = "快狗视频"
    toast("开始快狗视频") 
    应用是否在前台(包名, 应用名称)
    sleep(5000)
    findBtnExit("ll_like")
    sleep(2000)
    for(var i=0;i<80;i++){
        应用是否在前台短(包名, 应用名称)
        log("浏览视频-->"+(i+1))
        sleep(20000)
        视频类滑动()
        sleep(2000)
    }
    toast("结束")
}

var 主线程 = threads.start(function () {
     
    while (true) {
        sleep(4000)
    
        run()
    }
}
);


function 辅助() {
    查找控件第一个直接点击(text, "忽略")
    查找控件第一个直接点击(text, "继续观看")
    查找控件第一个直接点击(desc, "继续观看")
    查找控件第一个直接点击(text, "微信一键登录")
    查找控件第一个直接点击(desc, "微信一键登录")
    查找控件第一个直接点击(text, "继续播放")
    查找控件第一个直接点击(desc, "继续播放")
    查找控件第一个直接点击(text, "继续赚钱")
    查找控件第一个直接点击(desc, "继续赚钱")
    查找控件第一个直接点击(text, "一键签到")
    查找控件第一个直接点击(desc, "一键签到")
    查找控件第一个直接点击(text, "我知道了")
    查找控件第一个直接点击(desc, "我知道了")
    查找控件第一个直接点击(text, "立即开始")
    查找控件第一个直接点击(desc, "立即开始")
    查找控件第一个直接点击(text, "残忍拒绝")
    查找控件第一个直接点击(desc, "残忍拒绝")
    查找控件第一个直接点击(text, "确认登录")
    查找控件第一个直接点击(desc, "确认登录")
    查找控件第一个直接点击(text, "允许")
    查找控件第一个直接点击(desc, "允许")
    查找控件第一个直接点击(text, "同意")
    查找控件第一个直接点击(desc, "同意")
    查找控件第一个直接点击(text, "允许一次")
    查找控件第一个直接点击(desc, "允许一次")
    查找控件第一个直接点击(text, "忽略")
    查找控件第一个直接点击(desc, "忽略")
    查找控件第一个直接点击(text, "下次再说")
    查找控件第一个直接点击(desc, "下次再说")
    查找控件第一个直接点击(text, "确定")
    查找控件第一个直接点击(desc, "确定")
    查找控件第一个直接点击(text, "报告")
    if (找到 == true) {
        查找控件第一个直接点击(text, "取消")
        查找控件第一个直接点击(desc, "取消")
    }

    查找控件第一个直接点击(text, "前往清理")
    if (找到 == true) {
        查找控件第一个直接点击(text, "取消")
        查找控件第一个直接点击(desc, "取消")
    }
    查找控件第一个直接点击(text, "放弃")
    查找控件第一个直接点击(desc, "放弃")
    查找控件第一个直接点击(text, "知道了")
    查找控件第一个直接点击(desc, "知道了")
    查找控件第一个直接点击(text, "继续阅读")
    查找控件第一个直接点击(desc, "继续阅读")
    查找控件第一个直接点击(text, "点此继续播放")
    查找控件第一个直接点击(desc, "点此继续播放")
    查找控件第一个直接点击(text, "确认关闭")
    查找控件第一个直接点击(desc, "确认关闭")
    查找控件第一个直接点击(desc, "继续下载")
    查找控件第一个直接点击(text, "微信登录领取")
    查找控件第一个直接点击(text, "微信登录")
    查找控件第一个直接点击(text, "展开查看全文")
    查找控件第一个直接点击(text, "收下")
    查找控件第一个直接点击(text, "收下啦")
    查找控件第一个直接点击(text, "继续阅读")
    查找控件第一个直接点击(text, "好的")

    
    查找控件第一个直接点击(id, "image_close")
    查找控件第一个直接点击(id,"tt_video_ad_close")  
    查找控件第一个直接点击(id, "iv_dialog_ad")
    查找控件第一个直接点击(id, "colseBtnStaticView")        
    查找控件第一个直接点击(id, "dialog_close")
    查找控件第一个直接点击(id, "closeBtnAnimView")
    查找控件第一个直接点击(id, "luckyMoneyClose")
    查找控件第一个直接点击(id, "iv_guide_close")
}
var 辅助线程 = threads.start(function () {
    while (true) {
        sleep(4000)
        辅助()
    }
}
);

while (true) {
    sleep(3000)
    if (主线程.isAlive() == false) {
        var 主线程 = threads.start(function () {
            while (true) {
                sleep(4000)
                run()
            }
        }
        );

    }
    if (辅助线程.isAlive() == false) {
        var 辅助线程 = threads.start(function () {
            while (true) {
                sleep(4000)
                fuzhu()
            }
        }
        );
    }



} 