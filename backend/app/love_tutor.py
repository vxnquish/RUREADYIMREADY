def get_love_advice(message):
    if "짝사랑" in message:
        return "짝사랑은 어렵지만, 솔직한 표현이 가장 중요해요!"
    elif "이별" in message:
        return "힘든 시간이겠지만, 스스로를 먼저 돌보는 게 우선이에요."
    else:
        return f"'{message}'에 대해선 더 많은 정보를 알려주세요 💡"
