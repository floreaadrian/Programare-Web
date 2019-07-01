using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using MySql.Data.MySqlClient;

namespace Subject4
{
    public class Version
    {
        public int IDVersion { get; set; }
        public int IDObject { get; set; }
        public int IDUser { get; set; }
        public string content { get; set; }
        public DateTime time { get; set; }
    }

    public class Content
    {
        public Content(string content1) => this.content = content1;
        public string content { get; set; }
    }
    public class Con1Class : ApiController
    {
        MySqlConnection con = new MySqlConnection(@"Data Source=localhost;port=3306;Initial Catalog=subject4;User Id=root;password=''");

        [Route("api/con1/obj")]
        [HttpGet]
        public Content Get(int id)
        {
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "select * FROM `Versions` WHERE IDObject=" + id + "";
            cmd.ExecuteNonQuery();
            DataTable dt = new DataTable();
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            da.Fill(dt);
            String textContent = "";
            foreach (DataRow dr in dt.Rows)
            {
                textContent += dr["content"].ToString();
            }
            if (textContent == "")
            {
                cmd.CommandText = "INSERT INTO `Versions`(`IDVersion`, `IDObject`, `IDUser`, `content`, `time`) VALUES" +
                    " ([value-1],[value-2],[value-3],[value-4],[value-5])";
                cmd.ExecuteNonQuery();
            }

            con.Close();
            return new Content(textContent);
        }
    }
}
