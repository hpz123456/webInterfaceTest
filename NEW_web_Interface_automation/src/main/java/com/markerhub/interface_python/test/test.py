
import json


def test(obj):

    print(type(obj))
    print(obj[0])
    print(type(obj[0]))

    obj1 = [str(o) for o in obj]
    print(type(obj1[0]))
    print(obj1[0])

    js = json.loads(obj1[0])
    print(js)
    print(type(js))
    print(js["string"])

    return obj

if __name__ == '__main__':

    obj = ['{"string":"qwewqewqe","integer":123}'];
    test(obj)