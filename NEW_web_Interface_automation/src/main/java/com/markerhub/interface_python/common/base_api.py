# coding:utf-8
import json
import requests



def send_requests(s, testdata):
    '''封装requests请求'''
    #method = request_method请求方式
    method = testdata["request_method"]
    #url:接口地址
    url = testdata["url"]
    # url后面的params参数
    try:
        params = eval(testdata["params"])
    except:
        params = None
    # 请求头部headers
    try:
        headers = eval(testdata["headers"])
        print("请求头部：%s" % headers)
    except:
        headers = None
    # post请求body类型
    # type = testdata["type"]

    test_nub = testdata['id']
    print("*******正在执行用例：-----  %s  ----**********" % test_nub)
    print("请求方式：%s, 请求url:%s" % (method, url))
    print("请求params：%s" % params)

    cookie = testdata["cookid"]
    # post请求data内容
    try:
        data = eval(testdata["data"])
    except:
        data = {}

    # 判断传data数据还是json
    # if type == "data":
    #     body = bodydata
    # elif type == "json":
    data = json.dumps(data)
    # else:
    #     body = bodydata
    # if method == "post": print("post请求body类型为：%s ,body内容为：%s" % (type, body))

    verify = False
    res = {}   # 接受返回数据

    try:
        r = requests.request(method=method,
                      url=url,
                      params=params,
                      headers=headers,
                      data=data,
                      verify=verify,
                      cookies = cookie
                       )
        print("页面返回信息：%s" % r.content.decode("utf-8"))
        res['id'] = testdata['id']
        res['rowNum'] = testdata['rowNum']
        res["statuscode"] = str(r.status_code)  # 状态码转成str
        res["text"] = r.content.decode("utf-8")
        res["times"] = str(r.elapsed.total_seconds())   # 接口请求时间转str
        if res["statuscode"] != "200":
            res["error"] = res["text"]
        else:
            res["error"] = ""
        res["msg"] = ""
        if testdata["checkpoint"] in res["text"]:
            res["result"] = "pass"
            print("用例测试结果:   %s---->%s" % (test_nub, res["result"]))
        else:
            res["result"] = "fail"
        return res
    except Exception as msg:
        res["msg"] = str(msg)
        return res
