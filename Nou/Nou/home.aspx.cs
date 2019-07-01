using System;
using System.Web;
using System.Web.UI;
using System.Data;
using MySql.Data.MySqlClient;

namespace Nou
{

    public partial class home : System.Web.UI.Page
    {
        MySqlConnection con = new MySqlConnection(@"Data Source=localhost;port=3306;Initial Catalog=labIdkC;User Id=root;password=''");

        String table;
        Int32 page=1,start_from=0,record_per_page=4;
        Boolean stillHaving = false;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["username"] == null)
            {
                Response.Redirect("Default.aspx");
            }
        }

        protected void LogoutClick(object sneder, EventArgs e)
        {
            Session.Clear();
            Response.Redirect("Default.aspx");
        }


        protected void LoadTabData(object sneder, EventArgs e)
        {
            this.loadFromPage();
        }

        protected void prev(object sneder, EventArgs e)
        {
            if (this.page > 1)
                this.page--;
            this.loadFromPage();
        }

        protected void next(object sneder, EventArgs e)
        { 
            this.page++;
            this.loadFromPage();
        }

        private void loadFromPage() {
            con.Open();
            this.start_from = (this.page - 1) * this.record_per_page;
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "select * from reports where user=" + Int32.Parse(Session["id"].ToString())
                +" ORDER BY id LIMIT " +this.start_from + ", " + this.record_per_page;
            cmd.ExecuteNonQuery();
            DataTable dt = new DataTable();
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            da.Fill(dt);
            table = "";
            table += "<table border='1'>";
            table += "<tr><th>Id</th><th>Type</th><th>Severity</th><th>Date</th><th>User</th><th>Log</th></tr>";
            this.stillHaving = false;
            foreach (DataRow dr in dt.Rows)
            {
                this.stillHaving = true;
                table += "<tr>";
                table += "<td>" + dr["id"];
                table += "<td>" + dr["type"];
                table += "<td>" + dr["severity"];
                table += "<td>" + dr["date"];
                table += "<td>" + dr["user"];
                table += "<td>" + dr["log"];
            }
            if (!this.stillHaving && this.page>1)
                this.page--;
            table += "</table>";
            placeholder1.Controls.Add(new LiteralControl { Text = table });
            con.Close();
            
        }

        protected void addReportFunc(object sneder, EventArgs e)
        {
            String typeV = type.Text;
            String severityV = severity.Text;

            String dateV = date.Text;
            DateTime formatForMySql = DateTime.Parse(dateV);
            String dataTo = formatForMySql.ToString("o");
            String userId = Session["id"].ToString();
            String logV = log.Text;
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "insert into reports(type,severity,date,user,log) values ('"
                 + typeV + "','" + severityV + "','" + dataTo + "'," + userId + ",'" + logV + "')";
            cmd.ExecuteNonQuery();
            type.Text = string.Empty;
            severity.Text = string.Empty;
            log.Text = string.Empty;
            con.Close();
            this.loadFromPage();
        }

        protected void deleteReportFunc(object sneder, EventArgs e)
        {
            String reportIdV = reportID.Text;
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "delete from reports where id=" + reportIdV;
            cmd.ExecuteNonQuery();
            con.Close();
            reportID.Text = string.Empty;
            this.loadFromPage();
        }

        protected void filterFunc(object sneder, EventArgs e)
        {
            con.Open();
            MySqlCommand cmd = con.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = "select * from reports where user=" + Int32.Parse(Session["id"].ToString())
                + " and severity='"+ severityFilter.Text+ "'";
            cmd.ExecuteNonQuery();
            DataTable dt = new DataTable();
            MySqlDataAdapter da = new MySqlDataAdapter(cmd);
            da.Fill(dt);
            table = "";
            table += "<table border='1'>";
            table += "<tr><th>Id</th><th>Type</th><th>Severity</th><th>Date</th><th>User</th><th>Log</th></tr>";
            this.stillHaving = false;
            foreach (DataRow dr in dt.Rows)
            {
                this.stillHaving = true;
                table += "<tr>";
                table += "<td>" + dr["id"];
                table += "<td>" + dr["type"];
                table += "<td>" + dr["severity"];
                table += "<td>" + dr["date"];
                table += "<td>" + dr["user"];
                table += "<td>" + dr["log"];
            }
            table += "</table>";
            placeholder2.Controls.Add(new LiteralControl { Text = table });
            con.Close();

        }
    }
}
