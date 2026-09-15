import 'user.dart';

class AuthResponse {
  final User user;
  final String? token;
  final String message;

  const AuthResponse({
    required this.user,
    this.token,
    this.message = '',
  });

  factory AuthResponse.fromJson(Map<String, dynamic> json) {
    return AuthResponse(
      user: User(
        id: json['id'] as int,
        username: json['username'] as String,
        email: json['email'] as String,
        role: json['role'] as String,
      ),
      token: json['token'] as String?,
      message: json['message'] as String? ?? '',
    );
  }
}