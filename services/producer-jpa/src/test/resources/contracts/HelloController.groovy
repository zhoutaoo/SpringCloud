import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url('/hello') {
            queryParameters {
                parameter("name", "zhangsan")
            }
        }

    }
    response {
        status 200
        body("""
  {
    "code": "000000",
    "mesg": "处理成功"
  }
  """)
        headers {
            header('Content-Type': 'application/json;charset=UTF-8')
        }
    }
}