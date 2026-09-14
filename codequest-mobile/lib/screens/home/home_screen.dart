import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String message = 'Connecting...';

  @override
  void initState() {
    super.initState();
    checkBackend();
  }

  Future<void> checkBackend() async {
    try {
      final dio = Dio(
        BaseOptions(
          baseUrl: 'http://localhost:8080/api',
          connectTimeout: const Duration(seconds: 10),
          receiveTimeout: const Duration(seconds: 10),
          headers: {'Content-Type': 'application/json'},
        ),
      );

      final response = await dio.get('/health');

      if (!mounted) return;

      setState(() {
        message = response.data.toString();
      });
    } catch (e) {
      if (!mounted) return;

      setState(() {
        message = 'Backend connection failed\n$e';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('CodeQuest')),
      body: Center(
        child: Text(
          message,
          textAlign: TextAlign.center,
          style: const TextStyle(fontSize: 20),
        ),
      ),
    );
  }
}
