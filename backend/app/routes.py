from flask import request, jsonify
from app import app
from app.love_tutor import get_love_advice
from app.recommend import get_date_recommendation

@app.route("/api/chat", methods=["POST"])
def chat():
    data = request.get_json()
    message = data.get("message", "")
    response = get_love_advice(message)
    return jsonify({"response": response})

@app.route("/api/recommend", methods=["GET"])
def recommend():
    location = request.args.get("location", "서울")
    results = get_date_recommendation(location)
    return jsonify({"result": results})
