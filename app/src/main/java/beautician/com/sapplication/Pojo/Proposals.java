package beautician.com.sapplication.Pojo;

/**
 * Created by Amaresh on 11/23/17.
 */

public class Proposals {
    String id,service_request_id,remarks,category,status,created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService_request_id() {
        return service_request_id;
    }

    public void setService_request_id(String service_request_id) {
        this.service_request_id = service_request_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Proposals(String id, String service_request_id, String remarks, String status, String created) {
        this.id=id;
        this.service_request_id=service_request_id;
        this.remarks=remarks;
        this.status=status;
        this.created=created;

    }
}
