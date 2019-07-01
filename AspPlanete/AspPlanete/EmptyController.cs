using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace AspPlanete
{
    public class Ceva
    {
        public int id { get; set; }
        public string name { get; set; }
    }
    public class EmptyController : ApiController
    {
        [Route("api/empty/names")]
        public IEnumerable<string> Get()
        {
            return new string[] { "student1", "student2" };
        }

        [Route("api/empty")]
        public List<Ceva> Post(List<Ceva> value)
        {
            return value;
        }

    }
}
