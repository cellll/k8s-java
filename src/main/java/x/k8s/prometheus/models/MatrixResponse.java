package x.k8s.prometheus.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MatrixResponse {
    String status;
    MatrixData data;

    public static class MatrixData {
        String resultType;
        List<MatrixResult> result;
        
		public String getResultType() {
			return resultType;
		}
		public void setResultType(String resultType) {
			this.resultType = resultType;
		}
		public List<MatrixResult> getResult() {
			return result;
		}
		public void setResult(List<MatrixResult> result) {
			this.result = result;
		}
    }
    

    public static class MatrixResult {
        Map<String, String> metric;
        List<List<BigDecimal>> values;

        @Override
        public String toString() {
            return String.format(
                "metric: %s\nvalues: %s",
                metric.toString(),
                values == null ? "" : values.toString()
            );
        }

		public Map<String, String> getMetric() {
			return metric;
		}

		public void setMetric(Map<String, String> metric) {
			this.metric = metric;
		}

		public List<List<BigDecimal>> getValues() {
			return values;
		}

		public void setValues(List<List<BigDecimal>> values) {
			this.values = values;
		}


    }


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MatrixData getData() {
		return data;
	}

	public void setData(MatrixData data) {
		this.data = data;
	}
    
    
    
    
}
