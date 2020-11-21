using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    public class output
    {
        public static string put()
        {


            {
                return Json<dynamic>(new { number = "201822070430", password = "ll953376517", name = "朱希琳" });
            }
        }

        private static string Json<T>(object p)
        {
            throw new NotImplementedException();
        }
    }
}