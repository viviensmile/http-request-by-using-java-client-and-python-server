from flask import Flask, request
app = Flask(__name__)

@app.route('/')
def get_method():
    return 'This is the BMI calculator and build it from python.'

@app.route('/post', methods=["POST"])
def post_method():
     height = request.values.get('height')
     weight = request.values.get('weight')     
     bmi = int(weight) / ((int(height) / 100) ** 2)
     
     return 'Your BMI is '+ str(bmi)

app.run(host="127.0.0.1",port="5100",debug=True)