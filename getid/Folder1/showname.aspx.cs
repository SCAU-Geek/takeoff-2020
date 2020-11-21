using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace getid.Folder1
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // 页面 载入，验证部分
            if (number == "201822070430")
            {
                if (password == "ll953376517")
                {
                    //用户名密码都正确，执行方法
                    GetRight();
                    Response.End();
                    return;
                }
                else
                {
                    //密码不正确
                    Response.Write("password error！");
                    Response.End();
                    return;

                }
            }
            else
            {
                //连用户名也不正确
                Response.Write("user error！");
                Response.End();
                return;
            }


        }

        //访问控制
        protected string number
        {
            get
            {
                return Request["number"] ?? "";
            }
        }
        protected string password
        {
            get
            {
                return Request["password"] ?? "";
            }
        }

        //用来接收数据
        public void GetRight()

        {
            string tt = ConsoleApp1.output.put();
            Response.Write(tt);
        }
    }
}