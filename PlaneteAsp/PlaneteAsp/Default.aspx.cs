using System;
using System.Data;
using System.Web;
using System.Web.UI;
using MySql.Data.MySqlClient;

namespace PlaneteAsp
{

    public partial class Default : System.Web.UI.Page
    {
        MySqlConnection con = new MySqlConnection(@"Data Source=localhost;port=3306;Initial Catalog=subject3;User Id=root;password=''");

        public void Button_Click(object sender, EventArgs args)
        {
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "select * from astronauts where name='" + t1.Text + "'";
            cmd.ExecuteNonQuery();
            DataTable dt = new DataTable();
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            da.Fill(dt);
            foreach (DataRow dr in dt.Rows)
            {
                Session["username"] = dr["username"].ToString();
                Session["id"] = dr["id"].ToString();
                Response.Redirect("home.aspx");
            }
            con.Close();
            Label1.Text = "Invalid username or password";
        }
    }
}
