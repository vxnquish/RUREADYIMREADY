from app import app
from flask import request, jsonify
from app.love_tutor import get_love_advice
from app.recommend import get_date_recommendation

@app.route('/api/chat', methods=['POST'])
def chat():
    data = request.get_json()
    question = data.get('message')
    answer = get_love_advice(question)
    return jsonify({'response': answer})

@app.route('/api/recommend', methods=['GET'])
def recommend():
    location = request.args.get('location', '서울')
    result = get_date_recommendation(location)
    return jsonify({'result': result})
