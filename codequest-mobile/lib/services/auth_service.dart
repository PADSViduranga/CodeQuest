import '../core/network/api_client.dart';
import '../models/auth_response.dart';

class AuthService {
  final ApiClient apiClient;

  AuthService({required this.apiClient});

  Future<AuthResponse> register({
    required String username,
    required String email,
    required String password,
  }) async {
    final response = await apiClient.dio.post(
      '/auth/register',
      data: {
        'username': username,
        'email': email,
        'password': password,
      },
    );

    return AuthResponse.fromJson(
      response.data as Map<String, dynamic>,
    );
  }

  Future<AuthResponse> login({
    required String email,
    required String password,
  }) async {
    final response = await apiClient.dio.post(
      '/auth/login',
      data: {
        'email': email,
        'password': password,
      },
    );

    return AuthResponse.fromJson(
      response.data as Map<String, dynamic>,
    );
  }
}