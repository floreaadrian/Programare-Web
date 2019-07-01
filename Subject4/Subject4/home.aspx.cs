using System;
using System.Data;
using System.Web;
using System.Web.UI;
using MySql.Data.MySqlClient;

namespace Subject4
{

    public partial class home : System.Web.UI.Page
    {
        MySqlConnection con = new MySqlConnection(@"Data Source=localhost;port=3306;Initial Catalog=subject4;User Id=root;password=''");

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["user"] == null)
            {
                Response.Redirect("Default.aspx");
            }
        }
    }
}
