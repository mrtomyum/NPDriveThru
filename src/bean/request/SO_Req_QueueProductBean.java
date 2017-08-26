package bean.request;

public class SO_Req_QueueProductBean {
		private int queue_id;
		private String access_token;
		
		
		public SO_Req_QueueProductBean() {
			super();
			// TODO Auto-generated constructor stub
		}


		public SO_Req_QueueProductBean(int queue_id, String access_token) {
			super();
			this.queue_id = queue_id;
			this.access_token = access_token;
		}


		public int getQueue_id() {
			return queue_id;
		}


		public void setQueue_id(int queue_id) {
			this.queue_id = queue_id;
		}


		public String getAccess_token() {
			return access_token;
		}


		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
		
		
}
