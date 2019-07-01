using System;
using System.Data;
using System.Web;
using System.Web.UI;
using MySql.Data.MySqlClient;

namespace Subject4
{

    public partial class Default : System.Web.UI.Page
    {
        MySqlConnection con = new MySqlConnection(@"Data Source=localhost;port=3306;Initial Catalog=subject4;User Id=root;password=''");

        public void Button_Click(object sender, EventArgs args)
        {
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            String password = t2.Text;
            String username = t1.Text;
            int len = password.Length;
            if (len > username.Length)
                len = username.Length;
            String newPass = "";
            for(int i = 0; i < len; ++i)
            {
                newPass += username[i];
                newPass += password[i];
            }
            if (len == username.Length)
            {
                for (int i = len; i < password.Length; i++)
                    newPass += password[i];
            }
            else
            {
                for (int i = len; i < username.Length; i++)
                    newPass += username[i];
            }
            cmd.CommandText = "select * from Users where user='" + username + "' and hash='"+ newPass + "'";
            cmd.ExecuteNonQuery();
            DataTable dt = new DataTable();
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            da.Fill(dt);
            Label1.Text = newPass;
            foreach (DataRow dr in dt.Rows)
            {
                Session["user"] = dr["user"].ToString();
                Session["id"] = dr["ID"].ToString();
                Response.Redirect("home.aspx");
            }
            con.Close();
        }
    }
}
