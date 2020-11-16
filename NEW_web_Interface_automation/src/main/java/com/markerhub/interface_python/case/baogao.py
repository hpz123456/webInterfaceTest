def mk_test_name(name, value, index=0):
    """
    Generate a new name for a test case.

    It will take the original test name and append an ordinal index and a
    string representation of the value, and convert the result into a valid
    python identifier by replacing extraneous characters with ``_``.

    We avoid doing str(value) if dealing with non-trivial values.
    The problem is possible different names with different runs, e.g.
    different order of dictionary keys (see PYTHONHASHSEED) or dealing
    with mock objects.
    Trivial scalar values are passed as is.

    A "trivial" value is a plain scalar, or a tuple or list consisting
    only of trivial values.
    """

    # Add zeros before index to keep order
    index = "{0:0{1}}".format(index + 1, index_len)
    if not is_trivial(value):  # 如果不符合value的要求，则直接返回用例名称_下标作为最终测试用例名字。
        return "{0}_{1}".format(name, index)
    # 如果数据是list，则获取字典当中第一个数据作为测试用例名称
    if type(value) is list:
        try:
            value = value[0]
        except:
            return "{0}_{1}".format(name, index)
    try:
        value = str(value)
    except UnicodeEncodeError:
        # fallback for python2
        value = value.encode('ascii', 'backslashreplace')
    test_name = "{0}_{1}_{2}".format(name, index, value)
    return re.sub(r'\W|^(?=\d)', '_', test_name)