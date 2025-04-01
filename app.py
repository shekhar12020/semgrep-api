import json
from flask import Flask, request, jsonify
import subprocess
import os

app = Flask(__name__)
UPLOAD_FOLDER = './uploads'
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

@app.route('/analyze', methods=['POST'])
def analyze_code():
    if 'file' not in request.files:
        return jsonify({"error": "No file uploaded."}), 400

    file = request.files['file']
    if file.filename == '':
        return jsonify({"error": "No file selected."}), 400

    filepath = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(filepath)

    try:
        # Run Semgrep analysis
        command = [
            'semgrep',
            '--config', './semgrep_rules.yml',
            '--json',
            filepath
        ]
        result = subprocess.run(command, capture_output=True, text=True)

        if result.returncode == 0:
            # Format the JSON results properly
            findings = json.loads(result.stdout)
            return jsonify({
                "status": "success",
                "results": findings
            })
        else:
            return jsonify({"error": "Semgrep encountered an error."}), 500

    except Exception as e:
        return jsonify({"error": str(e)}), 500

    finally:
        if os.path.exists(filepath):
            os.remove(filepath)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=3000)

