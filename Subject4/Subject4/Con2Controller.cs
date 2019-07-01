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
        public Content(string content)
        {
            this.content = content;
        }
        public string content { get; set; }
    }

    public class VersionToPut
    {
        public int IDObject { get; set; }
        public int IDUser { get; set; }
        public string content { get; set; }
    }

    public class GetVers
    {
        public int IDObject { get; set; }
        public int IDUser { get; set; }
        public List<string> versions {get;set;}
    }

    public class ToIter
    {
        public int IDVersion { get; set; }
        public int IDUser { get; set; }
        public string content { get; set; }
    }

    public class Con2Controller:ApiController
    {
        MySqlConnection con = new MySqlConnection(@"Data Source=localhost;port=3306;Initial Catalog=subject4;User Id=root;password=''");

        [Route("api/con1/obj")]
        [HttpGet]
        public Content Get(int id,int idUser)
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

            con.Close();
            if (textContent == "")
            {
                con.Open();
                MySqlCommand cmd1 = con.CreateCommand();
                cmd1.CommandType = CommandType.Text;
                String sql = "INSERT INTO `Versions`(`IDVersion`, `IDObject`, `IDUser`, `content`,`time`) VALUES(" +
                        0 + ", " + id + "," + idUser + ",'" + "" + "','" + DateTime.Now.ToString("o") + "')";
                cmd1.CommandText = sql;
                cmd1.ExecuteNonQuery();
                con.Close();
            }
            return new Content(textContent);
        }

        [Route("api/con1/vers")]
        [HttpGet]
        public List<int> Get(int id)
        {
            List<int> lista = new List<int>();
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

            con.Close();
            foreach (DataRow dr in dt.Rows)
            {
                lista.Add(Int32.Parse(dr["IDVersion"].ToString()));
            }
            return lista;
        }

        [Route("api/con1/vers1")]
        [HttpPost]
        public List<ToIter> Post(GetVers nume)
        {
            List<ToIter> lista = new List<ToIter>();
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "select * FROM `Versions` WHERE IDObject=" + nume.IDObject + "";
            cmd.ExecuteNonQuery();
            DataTable dt = new DataTable();
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            da.Fill(dt);
            String textContent = "";
            foreach (DataRow dr in dt.Rows)
            {
                ToIter toIter = new ToIter();
                toIter.content = dr["content"].ToString();
                toIter.IDUser = Int32.Parse(dr["IDUser"].ToString());
                toIter.IDVersion = Int32.Parse(dr["IDVersion"].ToString());
                lista.Add(toIter);
            }

            con.Close();
            return lista;
        }


        public int getMaxVers(int objid)
        {
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "select max(IDVersion) as maximu FROM `Versions` WHERE IDObject=" + objid + "";
            cmd.ExecuteNonQuery();
            DataTable dt = new DataTable();
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            da.Fill(dt);
            int el=0;
            foreach (DataRow dr in dt.Rows)
            {
                el = Int32.Parse(dr["maximu"].ToString());
            }
            con.Close();
            return el;
        }

        [Route("api/con1/obj")]
        [HttpPut]
        public VersionToPut Put(VersionToPut vers)
        {
            int version = getMaxVers(vers.IDObject)+1;
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            String sql  = "INSERT INTO `Versions`(`IDVersion`, `IDObject`, `IDUser`, `content`,`time`) VALUES(" +
                    version + ", " + vers.IDObject + "," + vers.IDUser + ",'" + vers.content +"','"+DateTime.Now.ToString("o") + "')" ;
            cmd.CommandText = sql;
            cmd.ExecuteNonQuery();
            con.Close();
            return vers;
        }
    }
}
