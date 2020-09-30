$(function () {
    //下拉菜单
    $('.dropdown-toggle').dropdown()

    //点击删除按钮删除相应数据
    //找到按钮 点击时间
    $("button[data-delete]").click(function () {
        //this.找到对应 id
        let id = $(this).data('delete');
        $.ajax({
            url: `/book/${id}`,
            type: 'DELETE',
            success: () => window.location.reload()
        })
    })

    //设置标志位，当左键点击分类时不会跳出修改界面
    var flag = false

    //修改、删除分类函数
    $('.dropdown-tag').on({
        //阻止右键下拉菜单
        "contextmenu": function () {
            return false
        },
        //鼠标点击分类栏
        "mousedown": function (e) {
            //若点击鼠标右键，变为可编辑状态
            if (e.button == 2) {
                $(this).prop("contenteditable", true)
                $(this).removeClass('dropdown-item-a')
                flag = true
            }
        },
        //失去焦点
        "blur": function () {
            if ($(this).prop("contenteditable")) {
                $(this).prop("contenteditable", false)
                $(this).addClass('dropdown-item-a')
            }
            //取目前分类的信息
            var oldText = $(this).text()
            //判断修改文本是否为空
            if (oldText == "") {
                if (confirm("确定删除此分类？")) {
                    let id = $(this).data('id')
                    $.ajax({
                        url: `/category/${id}`,
                        type: 'DELETE'
                    })
                    $(this).remove()

                }
            } else {//修改分类中的信息
                if (flag) {
                    //获取新的分类
                    var newText = $(this).text()
                    if (confirm("确定将其改为--"+newText+"--类？")) {
                        const id = $(this).data('id')
                        const formData = {
                            "categoryId": id,
                            "categoryName": newText,
                            "parentId":0
                        };
                        $.ajax({
                            url: '/category/categoryUpdate',
                            type: 'PUT',
                            data: JSON.stringify(formData),
                            contentType: "application/json; charset=utf-8",
                            success: function (res) {
                                console.log(res)
                            }
                        });
                    }
                }
                flag = false
            }
        },
        "mousedown":function () {
            
        }
    })

    //添加分类
    $('.dropdown-addTag').click(function () {
        var addMsg = prompt("请添加分类：")
        const formData = {
            'categoryName':addMsg,
            'parentId':$(this).data('parent_id')
        }
        console.log(JSON.stringify(formData))
        if(addMsg){
            $.ajax({
                url:'/category/add',
                type:'POST',
                data: JSON.stringify(formData),
                contentType: "application/json; charset=utf-8",
                success: () => window.location.reload()
            })
        }
    })



})