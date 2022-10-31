public class Worker {
    private OnTaskDoneListener callBack;
    private OnTaskErrorListener falseBack;

    public Worker(OnTaskDoneListener callBack, OnTaskErrorListener falseBack) {
        this.callBack = callBack;
        this.falseBack = falseBack;
    }

    public void start(int numberOfTusks, int error) {
        for (int i = 1; i <= numberOfTusks; i++) {
            if (i == error) {
                falseBack.onError("ERROR: Task №" + i + " isn't done.");
            } else {
                callBack.onDone("Task №" + i + " is done.");
            }
        }
    }
}
